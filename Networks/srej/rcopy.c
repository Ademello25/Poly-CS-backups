#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
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

typedef enum State STATE;

enum State {
   DONE, START_STATE, FILENAME
};

void check_args(int argc, char**argv) {
   if (argc != 8) {
      printf("Wrong number of arguments");
      exit(-1);
   }
   if (strlen(argv[1]) > 1000) {
      printf("filename cannot be more than 1k characters\n");
      exit(-1);
   }
   if (strlen(argv[2]) > 1000) {
      printf("filename cannot be more than 1k characters\n");
      exit(-1);
   }
   if (atoi(argv[3]) < 400 || atoi(argv[3]) > 1400) {
      printf("Buffer size needs be in the 400-1400 range\n");
      exit(-1);
   }
   if (atoi(argv[4]) < 0 || atoi(argv[4]) >=1) {
      printf("Error rate needs to be between 0 and 1\n");
      exit(-1);
   }


}

STATE start_state(char** argv, Connection * server) {
   STATE returnValue = FILENAME;

   if (server->sk_num > 0) {
      close(server->sk_num);
   }
   
   if(udp_client_setup(argv[5], atoi(argv[6]), server) < 0) {
      returnValue = DONE;
   }
   else {
      returnValue = FILENAME;
   }
   

   return returnValue;
}

STATE filename(char * fname, int32_t buf_size, Connection * server) {
   int returnValue = START_STATE;
   uint8_t packet[MAX_LEN] ;
   uint8_t buf[MAX_LEN];
   uint8_t flag = 0;
   int32_t seq_num = 0;
   int32_t fname_len = strlen(fname) + 1;
   int32_t recv_check = 0;
   static int retryCount = 0;

   buf_size = htonl(buf_size);
   memcpy(buf, &buf_size, SIZE_OF_BUF_SIZE);
   memcpy(&buf[SIZE_OF_BUF_SIZE], fname, fname_len);
   
   send_buf(buf, fname_len + SIZE_OF_BUF_SIZE, server, FNAME, 0, packet);

   if ((returnValue = processSelect(server, &retryCount, START_STATE, 
               FNAME_OK, DONE)) == FNAME_OK) {
      recv_check = recv_buf(packet, MAX_LEN, server->sk_num, server,
            &flag, &seq_num);

      if(recv_check == -1) {
         returnValue = START_STATE;
      }
      else if(flag == FNAME_BAD) {
         printf("File %s not found\n", fname);
         returnValue = DONE;
      }
   }
   return returnValue;

}

int main(int argc, char **argv) {
   
   Connection server;
   //int32_t output_file_fd = 0;
   STATE state = START_STATE;
   check_args(argc, argv);

   sendtoErr_init(atof(argv[4]), DROP_OFF, FLIP_OFF, DEBUG_OFF, RSEED_OFF);

   while(state != DONE) {
      switch (state) {
         
         case START_STATE:
            printf("pre start state\n");
            state = start_state(argv, &server);
            printf("post start state\n");
            break;

         case FILENAME:
            printf("pre file state\n");
            state = filename(argv[1], atoi(argv[3]), &server);
            printf("post start state\n");
            break;

          case DONE:
            break;

      }
   }
      
   return 0;
}
