#ifndef _NETWORKS_H_
#define _NETWORKS_H_

#include <netinet/in.h>

#define MAX_LEN 1500
#define MAX_TRIES 10
#define LONG_TIME 10
#define SHORT_TIME 1
#define START_SEQ_NUM 1
#define SIZE_OF_BUF_SIZE 4

#define FNAME 1
#define DATA 2
#define FNAME_OK 3
#define FNAME_BAD 4
#define ACK 5
#define END_OF_FILE 6
#define EOF_ACK 7

enum SELECT {
   SET_NULL, NOT_NULL
};

typedef struct connection Connection;

struct connection {
   int32_t sk_num;
   struct sockaddr_in remote;
   uint32_t len;
};

#pragma pack(1)
typedef struct header Header;

struct header {
   uint32_t seq_num;
   uint16_t checksum;
   uint8_t flag;
};

//enum FLAG {
//   FNAME, DATA, FNAME_OK, FNAME_BAD, ACK, END_OF_FILE, EOF_ACK, CRC_ERROR = -1
//};

int32_t udp_server(int portNumber);
int32_t udp_client_setup(char * hostname, uint16_t port_num, Connection * connection);
int32_t select_call(int32_t socket_num, int32_t seconds, int32_t microseconds,
      int32_t set_null);
int32_t safeSend(uint8_t * packet, uint32_t len, Connection * connection);
int32_t safeRecv(int recv_sk_num, char * databuf, int len, Connection * connection);
int processSelect(Connection * client, int * retryCount, int selectTimeoutState,
      int dataReadyState, int doneState);
unsigned short in_cksum(unsigned short *addr,int len);
int32_t send_buf(uint8_t * buf, uint32_t len, Connection * connection, uint8_t flag, 
      uint32_t seq_num, uint8_t * packet);
int32_t recv_buf(uint8_t * buf, int32_t len, int32_t recv_sk_num, 
      Connection * connection, uint8_t * flag, int32_t * seq_num);
int createHeader(uint32_t len, uint8_t flag, uint32_t seq_num, uint8_t * packet);
int retrieveHeader( char * data_buf, int recv_len, uint8_t * flag, int32_t * seq_num);
#endif
