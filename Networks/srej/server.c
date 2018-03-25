#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/uio.h>
#include <sys/time.h>
#include <sys/wait.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <strings.h>
#include <sys/socket.h>
#include <netinet/in.h>
#include <netdb.h>

#include "cpe464.h"
#include "networks.h"

typedef enum State STATE;
enum State {
   START, DONE, FILENAME, RECV_DATA
};

int processArgs(int argc, char** argv) {
   int portNumber = 0;

   if(argc < 2 || argc > 3) {
      printf("Wrong # of arguments\n");
      exit(-1);
   }
   if(argc ==3) {
      portNumber = atoi(argv[2]);
   }
   else {
      portNumber = 0;
   }

   return portNumber;
}

STATE filename(Connection * client, uint8_t * buf, int32_t recv_len, int32_t * data_file, int32_t * buf_size) {
   uint8_t response[1];
   char fname[MAX_LEN];
   STATE returnValue = DONE;

   memcpy(buf_size, buf, SIZE_OF_BUF_SIZE);
   *buf_size = ntohl(*buf_size);
   memcpy(fname, &buf[sizeof(*buf_size)], recv_len - SIZE_OF_BUF_SIZE);

   if((client->sk_num = socket(AF_INET, SOCK_DGRAM, 0)) < 0) {
      perror("filename, open client socket");
      exit(-1);
   }

   if(((*data_file) = open(fname, O_WRONLY)) < 0) {
      send_buf(response, 0 ,client, FNAME_BAD, 0, buf);
      returnValue = RECV_DATA;
   }
   else {
      send_buf(response, 0, client, FNAME_OK, 0, buf);
      returnValue = RECV_DATA;
   }
   return returnValue;
}

void process_client(int32_t server_sk_num, uint8_t * buf, int32_t recv_len, Connection * client) {
   STATE state = START;
   int32_t data_file = 0;
   //int32_t packet_len = 0;
   //uint8_t packet[MAX_LEN];
   int32_t buf_size = 0;
   //int32_t seq_num = START_SEQ_NUM;

   while (state != DONE) {
      switch(state) {
         
         case START:
            state = FILENAME;
            break;

         case FILENAME:
            state = filename(client, buf, recv_len, &data_file, &buf_size);
            break;

         case RECV_DATA:
            break;

         case DONE:
            break;
      }

   }
}

void process_server(int server_sk_num) {
   pid_t pid = 0;
   int status = 0;
   uint8_t buf[MAX_LEN];
   Connection client;
   uint8_t flag = 0;
   int32_t seq_num = 0;
   int32_t recv_len = 0;
   
   while(1) {
      if(select_call(server_sk_num, LONG_TIME, 0, SET_NULL) == 1) {
         recv_len = recv_buf(buf, MAX_LEN, server_sk_num, &client, &flag, &seq_num);
         if(recv_len != -1) {
            if((pid = fork()) < 0) {
               perror("fork");
               exit(-1);
            }
            if(pid ==0) {
               process_client(server_sk_num, buf, recv_len, &client);
               exit(0);
            }
         }

         while(waitpid(-1, &status, WNOHANG) > 0){}
      }
   }
}



int main(int argc, char ** argv) {
   int32_t server_sk_num = 0;
   int portNumber = 0;

   portNumber = processArgs(argc, argv);
   sendtoErr_init(atof(argv[1]), DROP_OFF, FLIP_OFF, DEBUG_OFF, RSEED_OFF);
   
   server_sk_num = udp_server(portNumber);

   process_server(server_sk_num);

   return 0;
}
