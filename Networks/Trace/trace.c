/* Alexander DeMello
 * CPE 464
 * April 2016
 */

#include <stdio.h>
#include <stdlib.h>
#include <pcap/pcap.h>
#include <stdint.h>
#include <netinet/ether.h>
#include <string.h>
#include <arpa/inet.h>
#include "checksum.h"

#define MAC_LEN 6
#define TYPE_LEN 2
#define ARP_OFFSET 20
#define IP_OFFSET 14
#define IP_LEN 4
#define TCP_LEN 32

#define ARP 0x0806
#define IPv4 0x0800

#define UDP 0x11
#define TCP 0x06
#define ICMP 0x01

typedef struct __attribute__((__packed__)) ethHeader {
   u_char destAddr[MAC_LEN];
   u_char srcAddr[MAC_LEN];
   uint16_t type;
}ethHeader;

typedef struct __attribute__((__packed__)) arpHeader {
   uint16_t opcode;
   u_char srcMAC[MAC_LEN];
   u_char srcIP[IP_LEN];
   u_char destMAC[MAC_LEN];
   u_char destIP[IP_LEN];
}arpHeader;

typedef struct __attribute((__packed__)) ipHeader {
   uint8_t version;
   uint8_t headerLength;
   uint16_t totalLength;
   uint8_t dscp;
   uint8_t ecn;
   uint16_t ttl;
   uint16_t protocol;
   uint16_t checksum;
   u_char srcIP[IP_LEN];
   u_char destIP[IP_LEN];
}ipHeader;

typedef struct __attribute((__packed__)) tcpHeader {
   uint16_t srcPort;
   uint16_t destPort;
   uint32_t seq;
   uint32_t ack;
   uint16_t flags;
   uint16_t window;
   uint16_t checksum;
}tcpHeader;

void MACprinter(u_char * addr) {
   int i, mask = 0x00FF;  

   for(i=0; i < MAC_LEN; i++) {
      int convert = ((int)(addr[i] & mask));
      printf("%x", convert);

      if(i != 5) {
         printf(":");
      }
   }
   printf("\n");
}

void IPprinter(u_char * addr) {
   int i, mask = 0x00FF;

   for(i=0; i < IP_LEN; i++) {
      int convert = ((int)(addr[i] & mask));
      printf("%d", convert);

      if(i != 3) {
         printf(".");
      }
   }
   printf("\n");

}


void sniffer(u_char * args, const struct pcap_pkthdr * pktHdr, 
      const u_char *packet) {
   static int packNum = 1;
   struct ethHeader *ethernet = calloc(1, sizeof(ethHeader));
   struct arpHeader *arp = calloc(1, sizeof(arpHeader));
   struct ipHeader *ip = calloc(1, sizeof(ipHeader));
   //struct tcpHeader *tcp = calloc(1, sizeof(tcpHeader));
   //uint32_t *bigTemp = calloc(1, sizeof(uint32_t));
   uint16_t * typeTemp = calloc(1, sizeof(uint16_t));
   uint8_t * ipTemp = calloc(1, sizeof(uint8_t));
   //u_char * tcpChecksumHolder;
   uint8_t * checksumTemp;
   uint8_t mask = 0x0F;
   unsigned short isCorrect;

   printf("\nPacket number: %d  Packet Len: %d\n\n", packNum, pktHdr->len); 
 
   //Ethernet Header stuff
   printf("\tEthernet Header\n\t");
   memcpy(ethernet->destAddr, packet, MAC_LEN);
   memcpy(ethernet->srcAddr, packet + MAC_LEN, MAC_LEN);
   memcpy(typeTemp, packet + (MAC_LEN * 2), TYPE_LEN);
   printf("\tDest MAC: ");
   MACprinter(ethernet->destAddr);
   printf("\t\tSource MAC: ");  
   MACprinter(ethernet->srcAddr);
   printf("\t\tType: ");

   ethernet->type = ntohs(*typeTemp);
   
   //ARP STUFF
   if(ethernet->type == ARP) {
      printf("ARP\n\n\tARP header\n\t\tOpcode: ");
      memcpy(typeTemp, packet + ARP_OFFSET, 2);
      arp->opcode = ntohs(*typeTemp);
      memcpy(arp->srcMAC, packet + ARP_OFFSET + 2, MAC_LEN);
      memcpy(arp->srcIP, packet + ARP_OFFSET + 8, IP_LEN);
      memcpy(arp->destMAC, packet + ARP_OFFSET + 12, MAC_LEN);
      memcpy(arp->destIP, packet + ARP_OFFSET + 18, IP_LEN);
     
      if(arp->opcode == 1)
         printf("Request\n");
      else if(arp->opcode == 2)
         printf("Reply\n");
      else
         printf("SOMETHING WENT WRONG");

      printf("\t\tSender MAC: ");
      MACprinter(arp->srcMAC);
      printf("\t\tSender IP: ");
      IPprinter(arp->srcIP);
      printf("\t\tTarget MAC: "); 
      MACprinter(arp->destMAC);
      printf("\t\tTarget IP: ");
      IPprinter(arp->destIP);
   }
   //IP STUFF
   else if(ethernet->type == IPv4) {
      //version
      printf("IP\n\n\tIP Header\n\t\tIP Version: ");
      memcpy(ipTemp, packet + IP_OFFSET, 1);
      ip->version = (*ipTemp) & 0xF0;
      printf("%d\n\t\t", ip->version >> 4);
      
      //header length
      printf("Header Len (bytes): ");
      memcpy(ipTemp, packet + IP_OFFSET, 1);
      ip->headerLength = ((*ipTemp) & mask) * 4;
      printf("%d\n", ip->headerLength);
      printf("\t\tTOS subfields:\n");

      //total length
      memcpy(typeTemp, packet + IP_OFFSET + 2, 2);
      ip->totalLength = ntohs(*typeTemp);
      
      //TOS bits
      printf("\t\t   Diffserv bits: ");
      memcpy(ipTemp, packet + IP_OFFSET + 1, 1);
      ip->dscp = ((*ipTemp) & 0xFC) >> 2;
      ip->ecn = (*ipTemp) & 0x03;
      printf("%d\n", ip->dscp);
      printf("\t\t   ECN bits: %d\n",ip->ecn);
      
      printf("\t\tTTL: ");
      memcpy(ipTemp, packet + IP_OFFSET + 8, 1);
      ip->ttl = *ipTemp;
      printf("%d\n", ip->ttl);

      printf("\t\tProtocol: ");
      memcpy(ipTemp, packet + IP_OFFSET + 9, 1);
      ip->protocol = *ipTemp;
   
      if(ip->protocol == TCP)
         printf("TCP\n");
      else if(ip->protocol == UDP)
         printf("UDP\n");
      else if(ip->protocol == ICMP)
         printf("ICMP\n");
      else
         printf("Unknown\n");

      checksumTemp = calloc(ip->headerLength, sizeof(uint8_t));
      memcpy(checksumTemp, packet + IP_OFFSET, ip->headerLength);
      memcpy(typeTemp, packet + IP_OFFSET + 10, 2);
      ip->checksum = ntohs(*typeTemp);
      isCorrect = in_cksum((unsigned short*)checksumTemp, ip->headerLength);
      
      if(isCorrect == 0)
         printf("\t\tChecksum: Correct (0x%04x)\n", ip->checksum); 
      else
         printf("\t\tChecksum: Incorrect (0x%04x)\n", ip->checksum);
      
      memcpy(ip->srcIP, packet + IP_OFFSET + 12, IP_LEN);
      memcpy(ip->destIP,packet + IP_OFFSET + 16, IP_LEN);
      printf("\t\tSender IP: ");
      IPprinter(ip->srcIP);
      printf("\t\tDest IP: ");
      IPprinter(ip->destIP);
      printf("\n");

      //TCP
      /*if(ip->protocol == TCP) {
         memcpy(typeTemp,packet + IP_OFFSET + ip->headerLength, 2);
         tcp->srcPort = ntohs(*typeTemp);
         memcpy(typeTemp,packet +IP_OFFSET + ip->headerLength + 2, 2);
         tcp->destPort = ntohs(*typeTemp);
         printf("\tTCP Header\n\t\t");
         if(tcp->srcPort == 80)
            printf("Source Port:  HTTP\n");
         else
            printf("Source Port:  %d\n", tcp->srcPort);
         
         if(tcp->destPort == 80)
            printf("\t\tDest Port:  HTTP\n");
         else
            printf("\t\tDest Port:  %d\n",tcp->destPort);

         //rest of TCP data copy
         memcpy(bigTemp, packet + IP_OFFSET + ip->headerLength + 4, 4);
         tcp->seq = ntohl(*bigTemp);
         memcpy(bigTemp, packet + IP_OFFSET + ip->headerLength + 8, 4);
         tcp->ack = ntohl(*bigTemp);
         memcpy(typeTemp,packet + IP_OFFSET + ip->headerLength + 12, 2);
         tcp->flags = *typeTemp;
         memcpy(typeTemp,packet + IP_OFFSET + ip->headerLength + 14, 2);
         tcp->window = ntohs(*typeTemp);
         memcpy(typeTemp,packet + IP_OFFSET + ip->headerLength + 16, 2);
         tcp->checksum = ntohs(*typeTemp);

         printf("\t\tSequence Number: %llu\n",(unsigned long long)tcp->seq);
         printf("\t\tACK Number: %llu\n", (unsigned long long)tcp->ack);

         printf("\t\tData Offset (bytes): %d\n", ((tcp->flags & 0xF0) >> 4) * 4);
         if(tcp->flags & (1<<1)) 
            printf("\t\tSYN Flag: Yes\n");
         else
            printf("\t\tSYN Flag: No\n");

         if(tcp->flags & (1<<2)) 
            printf("\t\tRST Flag: Yes\n");
         else
            printf("\t\tRST Flag: No\n");

         if(tcp->flags & 1) 
            printf("\t\tFIN Flag: Yes\n");
         else
            printf("\t\tFIN Flag: No\n");

         if(tcp->flags & (1<<4)) 
            printf("\t\tACK Flag: Yes\n");
         else
            printf("\t\tACK Flag: No\n");

         printf("\t\tWindow Size: %d\n", tcp->window);
         
         // Total - IPheaderlength + 12 = checksum packet in bytes
         tcpChecksumHolder = calloc(ip->totalLength - ip->headerLength + 12, 1);
         memcpy(tcpChecksumHolder, ip->srcIP, IP_LEN);
         memcpy(tcpChecksumHolder + 4, ip->destIP, IP_LEN);
         memcpy(tcpChecksumHolder + 9, &ip->protocol, 1);
         *ipTemp = htons(ip->totalLength - ip->headerLength);
         memcpy(tcpChecksumHolder + 10, ipTemp, 2);
         memcpy(tcpChecksumHolder + 12, packet + IP_OFFSET + ip->headerLength
               , ip->totalLength - ip->headerLength);


         isCorrect = in_cksum((unsigned short*)tcpChecksumHolder, 
                  (ip->totalLength - ip->headerLength + 12));
         if(isCorrect == 0)
            printf("\t\tChecksum: Correct (0x%04x)\n", tcp->checksum);
         else
            printf("\t\tChecksum: Incorrect (0x%04x)\n", tcp->checksum);
      }*/
      
   }    
   else
      printf("Unknown PDU\n");
  
   /*free(ethernet);
   free(arp);
   free(ip);
   free(tcp);
   free(bigTemp);
   free(typeTemp);
   free(ipTemp);
   free(checksumTemp);*/
   packNum++;
}

int main(int argc, char* argv[]) {

   pcap_t *handle;
   char errBuff[100];

   if(argc < 2) {
      printf("There are not enough arguments\n");
      return 0;
   }

   handle = pcap_open_offline(argv[1], errBuff);
   pcap_loop(handle, 0, sniffer, NULL);

   return 0;
}
