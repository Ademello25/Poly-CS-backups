#include "libDisk.h"

int openDisk(char *filename, int nBytes){
  int fd = 0;
  int size = nBytes;

  //printf("FILENAME: %s, SIZE: %d \n", filename, nBytes);

  if(nBytes == 0){
    size = BLOCKSIZE * 2;
  }


  if( filename != NULL){
  
    fd = open(filename, O_CREAT | O_RDWR | O_TRUNC | O_NONBLOCK, S_IRUSR | S_IWUSR);
    
   
    if(fd < 0){
      printf("OPEN DID NOT SUCCEED\n");
    }
  }else{
    printf("FILENAME IS NULL\n");
  }

  return fd;
}

int readBlock(int disk, int bNum, void *block){
  if(disk == -1){
    return -1;
  }

  lseek(disk, bNum * BLOCKSIZE, SEEK_SET);
  int bytes_read = read(disk, block, BLOCKSIZE);

  if(bytes_read == -1){
    return -1;
  }

  return 0;
}

int writeBlock(int disk, int bNum, void *block){
  if(disk == -1){
    return -1;
  }

  lseek(disk, bNum *BLOCKSIZE, SEEK_SET);
  int bytes_writ = write(disk, block, BLOCKSIZE);
  if(bytes_writ == -1){
    return -1;
  }

  return 0;
}

void closeDisk(int disk){
  //fflush(disk);
  close(disk);
}




