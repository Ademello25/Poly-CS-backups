//Alexander DeMello rcopy functions

#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <sys/time.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

#include "networks.h"

#include "cpe464.h"

int32_t udp_server(int portNumber) {
   int sk = 0;
   struct sockaddr_in local;
   uint32_t len = sizeof(local);

   if((sk = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
      perror("socket");
      exit(-1);
   }

   local.sin_family = AF_INET;
   local.sin_addr.s_addr = INADDR_ANY;
   local.sin_port = htons(portNumber);

   if(bindMod(sk,(struct sockaddr *)&local, sizeof(local)) < 0) {
      perror("udp_server, bind");
      exit(-1);
   }

   getsockname(sk,(struct sockaddr *)&local, &len);
   printf("Using Port #: %d\n", ntohs(local.sin_port));

   return(sk);
}

int32_t udp_client_setup(char * hostname, uint16_t port_num, Connection * connection) {
   struct hostent * hp = NULL;
   
   connection->sk_num = 0;
   connection->len = sizeof(struct sockaddr_in);

   if((connection->sk_num = socket(AF_INET,SOCK_DGRAM,0)) < 0) {
      perror("udp_client_setup, socket");
      exit(-1);
   }

   connection->remote.sin_family = AF_INET;
   hp = gethostbyname(hostname);

   if(hp ==NULL) {
      printf("Host not found: %s\n", hostname);
      return -1;
   }

   memcpy(&(connection->remote.sin_addr),hp->h_addr,hp->h_length);

   connection->remote.sin_port = htons(port_num);
   return 0 ;
}


int32_t select_call(int32_t socket_num, int32_t seconds, int32_t microseconds, int32_t set_null) {
   fd_set fdvar;
   struct timeval aTimeout;
   struct timeval * timeout = NULL;

   if(set_null == NOT_NULL) {
      aTimeout.tv_sec = seconds;
      aTimeout.tv_usec = microseconds;
      timeout = &aTimeout;
   }

   FD_ZERO(&fdvar);
   FD_SET(socket_num, &fdvar);

   if(selectMod(socket_num+1,(fd_set *) &fdvar, (fd_set *) 0, (fd_set *) 0, timeout) < 0) {
      perror("select");
      exit(-1);
   }

   if(FD_ISSET(socket_num, &fdvar)) {
      return 1;
   }
   else {
      return 0;
   }
}

int32_t safeSend(uint8_t * packet, uint32_t len, Connection * connection) {
   int send_len = 0;

   if((send_len = sendtoErr(connection->sk_num, packet, len, 0, 
               (struct sockaddr *) &(connection->remote), connection->len)) < 0) {
      perror("in send_buff(), sendto() call");
      exit(-1);
   }

   return send_len;
}

int32_t safeRecv(int recv_sk_num, char* data_buf, int len, Connection * connection) {
   int recv_len =0;
   uint32_t remote_len = sizeof(struct sockaddr_in);

   if ((recv_len = recvfrom(recv_sk_num, data_buf, len, 0 , 
               (struct sockaddr *)&(connection->remote), &remote_len)) < 0) {
      perror("recv_buf, recvfrom");
      exit(-1);
   }
   
   connection->len = remote_len;
   return recv_len;
}

int processSelect(Connection * client, int * retryCount,
      int selectTimeoutState, int dataReadyState, int doneState) {
   int returnValue = dataReadyState;

   (*retryCount)++;
   if(*retryCount > MAX_TRIES) {
     printf("Send data %d time, no ACK< client is probably gone - so I'm terminating", MAX_TRIES);
     returnValue = doneState;
   }
   else {
      if(select_call(client->sk_num, SHORT_TIME, 0, NOT_NULL) == 1) {
         *retryCount = 0;
         returnValue = dataReadyState;
      }
      else {
         returnValue = selectTimeoutState;
      }
   }
   return returnValue;
}

unsigned short in_cksum(unsigned short *addr,int len)
{
        register int sum = 0;
        u_short answer = 0;
        register u_short *w = addr;
        register int nleft = len;

        /*
         * Our algorithm is simple, using a 32 bit accumulator (sum), we add
         * sequential 16 bit words to it, and at the end, fold back all the
         * carry bits from the top 16 bits into the lower 16 bits.
         */
        while (nleft > 1)  {
                sum += *w++;
                nleft -= 2;
        }

        /* mop up an odd byte, if necessary */
        if (nleft == 1) {
                *(u_char *)(&answer) = *(u_char *)w ;
                sum += answer;
        }

        /* add back carry outs from top 16 bits to low 16 bits */
        sum = (sum >> 16) + (sum & 0xffff);     /* add hi 16 to low 16 */
        sum += (sum >> 16);                     /* add carry */
        answer = ~sum;                          /* truncate to 16 bits */
        return(answer);
}

int32_t send_buf(uint8_t * buf, uint32_t len, Connection * connection,
      uint8_t flag, uint32_t seq_num, uint8_t * packet) {
   
   int32_t sentLen = 0;
   int32_t sendingLen = 0;

   if(len > 0) {
      memcpy(&packet[sizeof(Header)], buf, len);
   }

   sendingLen = createHeader(len, flag, seq_num, packet);
   sentLen = safeSend(packet, sendingLen, connection);

   return sentLen;
   
}

int createHeader(uint32_t len, uint8_t flag, uint32_t seq_num, uint8_t * packet) {
   Header * aHeader = (Header *) packet;
   uint16_t checksum = 0;

   seq_num = htonl(seq_num);
   memcpy(&(aHeader->seq_num), &seq_num, sizeof(seq_num));

   aHeader->flag = flag;

   memset(&(aHeader->checksum), 0, sizeof(checksum));
   checksum = in_cksum((unsigned short *) packet, len + sizeof(Header));
   memcpy(&(aHeader->checksum), &checksum, sizeof(checksum));

   return len + sizeof(Header);
}

int32_t recv_buf(uint8_t * buf, int32_t len, int32_t recv_sk_num,
            Connection * connection, uint8_t * flag, int32_t * seq_num){
   char data_buf[MAX_LEN];
   int32_t recv_len = 0;
   int32_t dataLen = 0;

   recv_len = safeRecv(recv_sk_num, data_buf, len, connection);

   dataLen = retrieveHeader(data_buf, recv_len, flag, seq_num);

   if (dataLen > 0)
      memcpy(buf, &data_buf[sizeof(Header)], dataLen);

   return dataLen;
}

int retrieveHeader(char * data_buf, int recv_len, uint8_t * flag, int32_t * seq_num) {
   Header * aHeader = (Header *) data_buf;
   int returnValue = 0;

   if(in_cksum((unsigned short *) data_buf, recv_len) != 0) {
      returnValue = -1;
   }
   else {
      *flag = aHeader->flag;
      memcpy(seq_num, &(aHeader->seq_num), sizeof(aHeader->seq_num));
      *seq_num = ntohl(*seq_num);

      returnValue = recv_len - sizeof(Header);
   }
   return returnValue;

}




