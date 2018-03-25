#include "libDisk.h"

typedef struct __attribute__((__packed__)) superblock{
	uint8_t block_code;
	uint8_t magic_num;
	uint16_t root_inode;
	uint8_t free_blocks[252];
} superblock;

typedef struct __attribute__((__packed__)) inode_block{
	uint8_t block_code;
	char filename[8];
	uint8_t read_only;
	uint8_t block_pointer;
	uint8_t char_pointer;
	time_t mod_time;
	time_t create_time;
	time_t access_time;
	uint8_t file_exents[232];
} inode_block;

typedef struct __attribute__((__packed__)) file_extent_block{
	uint8_t block_code;
	uint8_t free_space[255];
} file_extent_block;

typedef struct __attribute__((__packed__)) free_block{
	uint8_t block_code;
	uint8_t free_space[255];
} free_block;

typedef struct __attribute__((__packed__)) mounted_disk{
	char *filename;
	int fd;
} mounted_disk;

void printSuperBlock();
int size_of_fs;
mounted_disk md;

void printBlock(int block){
  md.fd = openDisk(md.filename, 0);
  printf("Printing block %d:\n", block);
  int index;
  uint8_t buffer[256] = {0};
  lseek(md.fd, 0, SEEK_SET);
  lseek(md.fd, block * BLOCKSIZE, SEEK_SET);
  read(md.fd, buffer, BLOCKSIZE);

  for(index = 0; index < 256; index++){
    printf("Index %d, Value %u\n", index, buffer[index]);
  }
  closeDisk(md.fd);
}
int tfs_mkfs(char *filename, int nBytes){
        int index;
	int success = openDisk(filename, nBytes);
	size_of_fs = nBytes;

        md.fd = success;
        md.filename = filename;

	if(nBytes < 512){
		size_of_fs = 512;
	}
        uint8_t buffer[BLOCKSIZE] = {0};
        
        for(index = 0; index < size_of_fs/BLOCKSIZE; index++){
          if(writeBlock(success, index, &buffer) == EWRITE_BLOCK){
            closeDisk(md.fd);
            return EWRITE_BLOCK;
          }
        }

	if(success != -1){
		superblock sup;
		inode_block iroot;

                memset(&sup, 0, BLOCKSIZE);
                memset(&iroot, 0, BLOCKSIZE);
	
                sup.block_code = 1;
		sup.magic_num = 0x45;
		sup.root_inode = 1;
		sup.free_blocks[0] = 4;
		sup.free_blocks[1] = 5;
                
		iroot.block_code = 2;
		strcpy((char *) iroot.filename, "/");
		time(&iroot.create_time); 
		time(&iroot.access_time);
		time(&iroot.mod_time);

                if(writeBlock(success,0, &sup) == EWRITE_BLOCK){
			closeDisk(md.fd);
                        return EWRITE_BLOCK;
		}
                
		if(writeBlock(success, 1, &iroot) == EWRITE_BLOCK){
                        closeDisk(md.fd);
			return EWRITE_BLOCK;
		}

		closeDisk(success);
	}else{
                closeDisk(md.fd);
		return EBAD_OPEN_DISK;
	}
        closeDisk(md.fd);
	return success;
}

int tfs_mount(char *filename){
	md.filename = filename; 

	md.fd = openDisk(filename, 0);

        printf("\t\tmd.fd mounting %d filename %s\n", md.fd, md.filename);
      
	if(md.fd == EBAD_OPEN_DISK){
                closeDisk(md.fd);
		return EBAD_OPEN_DISK;
	}
        closeDisk(md.fd);
	return md.fd;
}

int tfs_unmount(void){
    printf("\t\tunmounting fd %d filename %s\n", md.fd, md.filename);
	closeDisk(md.fd);
	md.filename = NULL;
	md.fd = -1;

	return 0;
}

uint8_t nextFD(int fd){

        md.fd = openDisk(md.filename, 0);
        superblock sup;
        int red = readBlock(md.fd, 0, &sup);
        uint8_t index;

	if(red == -1){
                closeDisk(md.fd);
		return EBAD_SYS_READ;
	}
        
        if( fd == 0 || fd == 1 || fd == 251){
          fd = 2;
        }
        for(index = fd; index < 252; index++){
           if(sup.free_blocks[index] == 0){
              close(md.fd);
              return index;
           }
        }

        closeDisk(md.fd);
	return -1;
}

void printSuperBlock(){
        md.fd = openDisk(md.filename, 0);
        int index;
        uint8_t super[256] = {0};
        printf("FILE fd: %u\n", md.fd);
         
        lseek(md.fd, 0, SEEK_SET);
        read(md.fd, super, 256);

        for(index = 0; index < 256; index ++){
          printf("At Index %d, Value: %u\n", index, super[index]);
        }

        closeDisk(md.fd);
}

uint8_t addFDtoSUP(fileDescriptor fd, int set){
        md.fd = openDisk(md.filename, 0);
	lseek(md.fd, 0, SEEK_SET);
        superblock sup;
        int red = read(md.fd, &sup, BLOCKSIZE);

	if(red < 0){
		return EBAD_SYS_READ;
	}

        int val = sup.free_blocks[fd];
	if((set == 0 && (val == 1 || val == 2 || val == 3))||
           (set == 1 && (val == 0 || val == 2))||
           (set == 2 &&  val == 1) ||
           (set == 3 &&  val ==0)){ 
		sup.free_blocks[fd] = set;
		lseek(md.fd, 0, SEEK_SET);
                if(write(md.fd, &sup, BLOCKSIZE) < 0){
			return EBAD_SYS_WRITE;
		}
	}else{
		printf("addFDtoSUP: usage error, fd is taken %d   %d\n", sup.free_blocks[fd], fd);
		return EFD_USED;
	}
        closeDisk(md.fd);
        
	return fd;
}

// 0 is free
// 1 is open
// 2 is closed
// 3 is data
fileDescriptor tfs_openFile(char *name){
        md.fd = openDisk(md.filename, 0);

        printf("\t\tAdding %s to File System %s\n", name, md.filename);
        if(md.fd < 0){
          printf("\t\tUnable to open \n");
        }

      	fileDescriptor fd = nextFD(2);
        if(addFDtoSUP(fd, 1) > 0){
		inode_block new_file;
                memset(&new_file, 0, BLOCKSIZE); 

		new_file.block_code = 2;
		strncpy(new_file.filename, name, 8);
		new_file.block_pointer = 0;
		new_file.char_pointer = 0;
		new_file.file_exents[0] = nextFD(fd);
                  
                md.fd = openDisk(md.filename, 0);
		if(time(&new_file.create_time) == -1){
                        closeDisk(md.fd);
			return EBAD_TIME;
		}
		if(time(&new_file.access_time) == -1){
                        closeDisk(md.fd);
			return EBAD_TIME;
		}
		if(time(&new_file.mod_time) == -1){
                        closeDisk(md.fd);
			return EBAD_TIME;
		}

		if(new_file.file_exents[0] < 0){
			printf("tfs_openFile: AWKWARD...it happened :p\n");
                        closeDisk(md.fd);  
			return ENO_NEXT_FD;
		}

		writeBlock(md.fd, fd, &new_file);
                closeDisk(md.fd);

	}else{
		printf("tfs_openFile: AWKWARD...it happened :p\n");
                closeDisk(md.fd);
		return EADD_TO_SUP;
	}	 
        closeDisk(md.fd);        
	return fd;
}

int tfs_closeFile(fileDescriptor FD){
        md.fd = openDisk(md.filename, 0);
	uint8_t buffer = 0;
	uint8_t two = 2;

	if(FD < 1){
		return EBAD_CLOSE_FILE;
	}

	lseek(md.fd, 4 + FD, SEEK_SET);
        int red = read(md.fd, &buffer,1);

	if(red < 0) {
                closeDisk(md.fd);
		return EBAD_SYS_READ;
	}

	if(buffer == 1 || buffer == 2){
		inode_block inode;
		lseek(md.fd, FD * BLOCKSIZE, SEEK_SET);
		if(read(md.fd, &inode, BLOCKSIZE) < 0) {
                        closeDisk(md.fd);
			return EBAD_SYS_READ;
		}

		if(time(&inode.access_time) == -1) {
                        closeDisk(md.fd);
			return EBAD_TIME;
		}
		if(writeBlock(md.fd, FD, &inode) == EWRITE_BLOCK) {
                        closeDisk(md.fd);
			return EWRITE_BLOCK;
		}
		
		if(addFDtoSUP(FD, two) == EADD_TO_SUP) {
                        closeDisk(md.fd);
			return EADD_TO_SUP;
		}
                closeDisk(md.fd);
		return 0;
	}
        closeDisk(md.fd);  
	printf("tfs_closeFile: YOU FUCKED UP %d\n", buffer);

	return EBAD_CLOSE_FILE;
}

int tfs_writeFile(fileDescriptor FD, char *buffer, int size){
	md.fd = openDisk(md.filename, 0);
        uint8_t cur_fd;
	int count = 0;
	file_extent_block file_ex;
        superblock sup;
	inode_block inode;

        //Bringing inode back into the local hood
        int err = readBlock(md.fd, FD, &inode);

	if(err < 0) {
                closeDisk(md.fd);
		return EBAD_SYS_READ;
	}
        //printBlock(FD);
	if(inode.read_only == 1) {
                closeDisk(md.fd);
		return EIS_READ_ONLY;
	}

	cur_fd = inode.file_exents[0];
	if(time(&inode.access_time) == -1) {
                closeDisk(md.fd);
		return EBAD_TIME;
	}
        md.fd = openDisk(md.filename, 0);

	if(readBlock(md.fd, 0, &sup) < 0) {
                closeDisk(md.fd);
		return EBAD_SYS_READ;
	}

	if(sup.free_blocks[FD] != 1){
		printf("tfs_writeFile: DIS FD AINT OPEN\n");
                closeDisk(md.fd);
		return EFD_NOT_OPEN;
	}

	while(size > 256){
		memcpy(&file_ex, buffer, BLOCKSIZE);
		buffer = buffer + BLOCKSIZE;
	        md.fd = openDisk(md.filename, 0);

		if(writeBlock(md.fd, cur_fd, &file_ex) == EWRITE_BLOCK) {
                        closeDisk(md.fd);
			return EWRITE_BLOCK;
		}
		
		inode.file_exents[count] = cur_fd;
	        	
		if(addFDtoSUP(cur_fd, 3) == EADD_TO_SUP) {
                        closeDisk(md.fd);
			return EADD_TO_SUP;
		}

		count++;
		cur_fd = nextFD(cur_fd);
		if(cur_fd == ENO_NEXT_FD) {
                        closeDisk(md.fd);
			return ENO_NEXT_FD;
		}
		size = size - BLOCKSIZE;
	}

	if(size != 0){
		memcpy(&file_ex, buffer, size);
                md.fd = openDisk(md.filename, 0);
		if(writeBlock(md.fd, cur_fd, &file_ex) == EWRITE_BLOCK) {
                        closeDisk(md.fd);
			return EWRITE_BLOCK;
		}
		inode.file_exents[count] = cur_fd;

		if(addFDtoSUP(cur_fd, 3) == EADD_TO_SUP) {
                        closeDisk(md.fd);
			return EADD_TO_SUP;
		}
	}

	inode.char_pointer = 0;
	inode.block_pointer = inode.file_exents[0];

	if(time(&inode.mod_time) == -1) {
                closeDisk(md.fd);
		return EBAD_TIME;
	}
        md.fd = openDisk(md.filename, 0);
	if(writeBlock(md.fd, FD, &inode) == EWRITE_BLOCK) {
                closeDisk(md.fd);
		return EWRITE_BLOCK;
	}
        closeDisk(md.fd);
	return 0;
}

int tfs_deleteFile(fileDescriptor FD){
	int index = 0;
    uint8_t cur_fd;
	uint8_t buffer[BLOCKSIZE];
	memset(buffer, 0, BLOCKSIZE);

	inode_block inode;
	lseek(md.fd, FD * BLOCKSIZE, SEEK_SET);
	if(read(md.fd, &inode, BLOCKSIZE) < 0) {
		return EBAD_SYS_READ;
	}
	if(inode.read_only == 1) {
		return EIS_READ_ONLY;
	}
	cur_fd = inode.file_exents[index];

	while(cur_fd != 0){
		if(addFDtoSUP(cur_fd, 0) == EADD_TO_SUP) {
			return EADD_TO_SUP;
		}
		if(writeBlock(md.fd, cur_fd, buffer) == EWRITE_BLOCK) {
			return EWRITE_BLOCK;
		}
		index++;
		cur_fd = inode.file_exents[index];
	}

	if(addFDtoSUP(FD, 0) == EADD_TO_SUP) {
		return EADD_TO_SUP;
	}
	if(writeBlock(md.fd, FD, buffer) == EWRITE_BLOCK) {
		return EWRITE_BLOCK;
	}

	return 0;
}

int tfs_readByte(fileDescriptor FD, char *buffer){
        md.fd = openDisk(md.filename, 0);
	uint8_t block_code;	
	inode_block inode;
        superblock sup;

	if(readBlock(md.fd, FD, &inode) < 0) {
		return EBAD_SYS_READ;
	}
	block_code = inode.block_code;

	if(time(&inode.access_time) == -1) {
		return EBAD_TIME;
	}

	md.fd = openDisk(md.filename, 0);
        if(readBlock(md.fd, 0, &sup) < 0) {
		return EBAD_SYS_READ;
	}
	if(sup.free_blocks[FD] != 1){
		printf("tfs_readByte: Val is %d\n", sup.free_blocks[FD]);
		return EFD_NOT_OPEN;
	}

	if(block_code != 2){	
		printf("tfs_readByte: block_code is %d\n", block_code);
		return EINV_BLOCK_TYPE;
	}

	if(inode.char_pointer < 0 || inode.block_pointer < 0){
		printf("tfs_readByte: Something wrong with pointers\n");
		return EBAD_CURSOR;
	}

        uint8_t buf[256] = {0};
        md.fd = openDisk(md.filename, 0);
	if(readBlock(md.fd, inode.block_pointer, &buf) < 0) {
		return EBAD_SYS_READ;
	}
        *buffer = buf[inode.char_pointer + 1];

  return 0;
}

int tfs_seek(fileDescriptor FD, int offest){
	inode_block inode;
	lseek(md.fd, FD * BLOCKSIZE, SEEK_SET);
	if(read(md.fd, &inode, BLOCKSIZE) < 0) {
		return EBAD_SYS_READ;
	}
	if(time(&inode.access_time) == -1) {
		return EBAD_TIME;
	}

	int character = offest % 256;
	int block = offest / 256;

	inode.block_pointer = inode.file_exents[block];
	inode.char_pointer = character;
	if(time(&inode.mod_time) == -1) {
		return EBAD_TIME;
	}

	return writeBlock(md.fd, FD, &inode);
}

int tfs_rename(fileDescriptor FD, char *new_name){
	inode_block inode;
	int size = strlen(new_name);
	lseek(md.fd, FD * BLOCKSIZE, SEEK_SET);
	if(read(md.fd, &inode, BLOCKSIZE) < 0) {
		return EBAD_SYS_READ;
	}
	if(time(&inode.access_time) == -1) {
		return EBAD_TIME;
	}

	if(size == 0){
		return EBAD_FILENAME;
	}

	if(size < 8){
		memset(inode.filename, 0, 8);
		memcpy(inode.filename, new_name, size);
	}else{
		memcpy(inode.filename, new_name, 8);
	}
	if(time(&inode.mod_time) < 0) {
		return EBAD_TIME;
	}

	return writeBlock(md.fd, FD, &inode);
}

void tfs_readdir(){
	int index;
	uint8_t val;
	char filename[9];
    char null = '\0';

	superblock sup;
	lseek(md.fd, 0, SEEK_SET);
	if(read(md.fd, &sup, BLOCKSIZE) < 0) {
		printf("BAD READ SYS FILE\n");
		return;
	}

	for(index = 0; index < 252; index++){
		val = sup.free_blocks[index];

		if(val == 5){
			printf("/ROOT: \n");
		}

		if(val == 1){
			lseek(md.fd, index * BLOCKSIZE + 1, SEEK_SET);
			if(read(md.fd, filename, 8) < 0) {
				printf("BAD READ SYS FILE\n");
				return;
			}
		    memcpy(filename + 8, &null, 1);
			printf("\t\t %s\n",filename);
		}
	}	
}

void tfs_readFileInfo(fileDescriptor FD){
	inode_block inode;
	lseek(md.fd, FD * BLOCKSIZE, SEEK_SET);
	if(read(md.fd, &inode, BLOCKSIZE) <0){
		printf("BAD READ SYS FILE\n");
		return;
	}

	if(inode.block_code == 2){
		printf("\t\tCreation time: %s\n", ctime(&inode.create_time));
		printf("\t\tAccess time: %s\n", ctime(&inode.access_time));
		printf("\t\tModification time: %s\n", ctime(&inode.mod_time));
	}else{
		printf("\t\tERRORtfs_readFileInfo: Not a valid File Descriptor\n");
		return;
	}
}

int tfs_makeRO(char *name){
	int index;
	uint8_t val;
	uint8_t one = 1;
	char filename[9];
    char null = '\0';

	superblock sup;
	lseek(md.fd, 0, SEEK_SET);
	if(read(md.fd, &sup, BLOCKSIZE) < 0) {
		return EBAD_SYS_READ;
	}

	for(index = 0; index < 252; index++){
		val = sup.free_blocks[index];

		if(val == 1){
			lseek(md.fd, index * BLOCKSIZE + 1, SEEK_SET);
			if(read(md.fd, filename, 8) < 0) {
				return EBAD_SYS_READ;
			}
			memcpy(filename + 8, &null, 1);
			if(strcmp(name, filename) == 0){
				lseek(md.fd, index * BLOCKSIZE + 9, SEEK_SET);
				if(write(md.fd, &one, 1) <0) {
					return EBAD_SYS_WRITE;
				}
			}
		}
	}
        return 0;
}

int tfs_makeRW(char *name){
	int index;
	uint8_t val;
	uint8_t zero = 1;
	char filename[9];

	superblock sup;
	lseek(md.fd, 0, SEEK_SET);
	if(read(md.fd, &sup, BLOCKSIZE) < 0) {
		return EBAD_SYS_READ;
	}

	for(index = 0; index < 252; index++){
		val = sup.free_blocks[index];

		if(val == 1){
			lseek(md.fd, index * BLOCKSIZE + 1, SEEK_SET);
			if(read(md.fd, filename, 8) < 0) {
				return EBAD_SYS_READ;
			}
			filename[9] = '\0';
			if(strcmp(name, filename) == 0){
				lseek(md.fd, index * BLOCKSIZE + 9, SEEK_SET);
				if(write(md.fd, &zero, 1) < 0) {
					return EBAD_SYS_WRITE;
				}
			}
		}
	}
    return 0;
}

int tfs_writeByte(fileDescriptor FD, int offest, unsigned int data){
	inode_block inode;
	lseek(md.fd, FD * BLOCKSIZE, SEEK_SET);
	if(read(md.fd, &inode, BLOCKSIZE) < 0) {
		return EBAD_SYS_READ;
	}
	if(inode.read_only == 1) {
		return EIS_READ_ONLY;
	}

	int character = offest % 256;
	int block = offest / 256;

	lseek(md.fd, block * BLOCKSIZE + character, SEEK_SET);
	if(write(md.fd, &data, 1) < 0) {
		return EBAD_SYS_WRITE;
	}

    return 0;
}

