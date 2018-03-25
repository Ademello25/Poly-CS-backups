#include "tinyFS.h"
#include <stdio.h>
#include <string.h>

void test_mkfs(){
	printf("\t Testing tfs_mkfs:\n");
	char filename[] = "tiny.txt";
	int nBytes = 10 * BLOCKSIZE;

	int ret = tfs_mkfs(filename, nBytes);
    //printf("After Returning\n");

    tfs_mount(filename);
    printSuperBlock();
    printBlock(1);

	if(ret < 0 ){
		printf("\t\t Unsuccessful in opening file: %d\n", ret);
	}else{
		printf("\t\tSuccess\n");
	}
}


void test_mount_unmount(){
	printf("\t Testing tfs_mount and tfs_unmount:\n");
        char file1[] = "tiny.txt";
        char file2[] = "huge.txt";
        int nBytes = 40 * BLOCKSIZE;
        
        printf("\t\tMounting %s\n", file1);
        int ret1 = tfs_mkfs(file1, nBytes);
        //printSuperBlock();


        printf("\t\tMounting %s\n", file2);
        int ret2 = tfs_mkfs(file2, nBytes);
        //printSuperBlock();  

        if(ret1 < 0 || ret2 < 0){
          printf("\t\tMkfs Error");
          return;
        }

        int fileDesc = tfs_mount(file1);
        if(fileDesc < 0){
          printf("\t\tInitial Mount Failed %d\n", fileDesc);
          return;
        }
        
        tfs_unmount();

        int fileDesc2 = tfs_mount(file2);
        if(fileDesc2 < 0){
          printf("\t\tSecound Mount Failed %d\n", fileDesc2);
          return;
        }
        tfs_unmount();
        printf("\t\tSuccess\n");
        return;
}


void test_addFDtoSUP(){
	printf("\t Testing tfs_addFDtoSUP:\n");
        char file1[] = "tiny.txt";
        int nBytes = 10 * BLOCKSIZE;
        printf("\t\tMaking File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);
        if(ret1 < 0){
          printf("\t\tERROR MAKING FILE SYSTEM\n");
        }
        printf("\t\tMounting File System\n");
        int mount = tfs_mount(file1);
        if(mount < 0 ){
            printf("\t\tERROR MOUNTING FILE SYSTEM\n");
        }
        
        //printSuperBlock();
        printf("\t\t Adding value 1 at Free Block 3\n");
        
        if(addFDtoSUP(3 ,1) < 0){
          printf("\t\tERROR ADD FD\n");
          return;
        }

        //printSuperBlock();
        printf("\t\t Adding value 0 at Free Block 3\n");

        if(addFDtoSUP(3,0) < 0){
          printf("\t\tERROR ADD FD\n");
          return;
        }

        //printSuperBlock();
        printf("\t\tSuccess\n");
}

void test_openFile_closeFile(){
	printf("\t Testing tfs_openFile and tfs_closeFile:\n");

        char file1[] = "tiny.txt";
        char file2[] = "tnedance";
        int nBytes = 40 * BLOCKSIZE;

        printf("\t\tMaking the File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);
        //printSuperBlock(); 

        if(ret1 < 0){
          printf("\t\tMAKING FILE SYSTEM ERROR: %d\n",ret1);
          return;
        }

        printf("\t\tMounting the File System\n");
        int fileDesc = tfs_mount(file1);
        //printSuperBlock();        

        if(fileDesc < 0){
          printf("\t\tMOUNTING FILE SYSTEM ERROR: %d\n", fileDesc);
          return;
        }
      
        printf("\t\tOpening the File\n");
        fileDescriptor fd = tfs_openFile(file2);
        
        //printf("\t\tReturned from OpenFile()\n");
        //printSuperBlock();
        if(fd < 0){
          printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd);
          return;
        }

        printf("\t\tClosing the File\n");
        int close = tfs_closeFile(fd);
        //printSuperBlock();

        if(close < 0){
          printf("\t\tCLOSE FILE FAILED: %d\n", close);
          return;
        }

        printf("\t\tUnmounting the File System\n");
        tfs_unmount();

        printf("\t\tSuccess\n");
}

void test_writeFile(){
        printf("\t Testing tfs_writeFile:\n");
        char file1[] = "tiny.txt";
        char file2[] = "file.txt";
        int nBytes = 10 * BLOCKSIZE;
        int i, error;
        char buffer[256];
        char resultByte;

        printf("\t\tMaking File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);

        if(ret1 < 0){
          printf("\t\tERROR MAKING FILE SYSTEM: %d\n", ret1);
        }

        printf("\t\tMounting File System\n");
        int mount = tfs_mount(file1);

        if(mount < 0 ){
            printf("\t\tERROR MOUNTING FILE SYSTEM: %d\n", mount);
        }

        printf("\t\tOpening file %s on system %s\n", file2, file1);
        fileDescriptor fd = tfs_openFile(file2);

        if(fd < 0){
          printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd);
          return;
        }

        printf("\t\tWriting File to File System\n");
        memset(buffer, 100, BLOCKSIZE);
        int err = tfs_writeFile(fd, buffer, BLOCKSIZE);

        if(err < 0) {
           printf("\t\tERROR IN WRITE FILE: %d\n", err);
           return;
        }

        for(i = 0; i < 256; i++) {
            error = tfs_readByte(fd, &resultByte);
            if(error < 0 || resultByte != 100) {
               printf("\t\tFailure: byte incorrect at index %d Val: %u ERROR: %d\n",i,resultByte, error);
               return;
            }
        }
        printf("\t\tSuccess\n");
}
 
void test_nextFD(){
	/*
    printf("\t Testing tfs_nextFD:\n");
    char file1[] = "tiny.txt";
    int nBytes = 10 * BLOCKSIZE;

    printf("\t\tMaking File System\n");
    int ret1 = tfs_mkfs(file1, nBytes);

    if(ret1 < 0){
      printf("\t\tERROR MAKING FILE SYSTEM\n");
    }

    printf("\t\tMOUNTING FILE SISYTEM\n");
    int mount = tfs_mount(file1);

    if(mount < 0 ){
        printf("\t\tERROR MOUNTING FILE SYSTEM\n");
    }*/

}

void test_deleteFile(){
	printf("\t Testing tfs_deleteFile:\n");
    char file1[] = "tiny.txt";
    char file2[] = "file.txt";
    int nBytes = 10 * BLOCKSIZE;
    char buffer[256];
    int i;

    printf("\t\tMaking File System\n");
    int ret1 = tfs_mkfs(file1, nBytes);

    if(ret1 < 0){
      printf("\t\tERROR MAKING FILE SYSTEM\n");
    }

    printf("\t\tMounting File System\n");
    int mount = tfs_mount(file1);

    if(mount < 0 ){
        printf("\t\tERROR MOUNTING FILE SYSTEM: %d\n", mount);
    }

    printf("\t\tOpening file %s on system %s\n", file2, file1);
    fileDescriptor fd = tfs_openFile(file2);

    if(fd < 0){
      printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd);
      return;
    }

    memset(buffer, 100, BLOCKSIZE);
    printf("\t\tWriting File to File System\n");

    int err = tfs_writeFile(fd, buffer, BLOCKSIZE);

    if(err < 0) {
       printf("\t\tERROR IN WRITE FILE: %d\n", err);
       return;
    }

    printf("\t\tDeleting File from System\n");
    err = tfs_deleteFile(fd);

    if(err < 0){
        printf("\t\tFAILED TO DELETE THE FILE: %d\n", err);
        return;
    }
    printf("\t\tSuccess\n");
    return;
}

void test_readByte(){
	/*printf("\t Testing tfs_readByte:\n");
        char file1[] = "tiny.txt";
        int nBytes = 10 * BLOCKSIZE;

        printf("\t\tMaking File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);

        if(ret1 < 0){
          printf("\t\tERROR MAKING FILE SYSTEM\n");
        }

        printf("\t\tMOUNTING FILE SISYTEM\n");
        int mount = tfs_mount(file1);

        if(mount < 0 ){
            printf("\t\tERROR MOUNTING FILE SYSTEM\n");
        }
        */
}

void test_seek(){
	printf("\t Testing tfs_seek:\n");
    char file1[] = "tiny.txt";
    char file2[] = "file.txt";
    int nBytes = 10 * BLOCKSIZE;
    char buffer[256];
    int i;

    printf("\t\tMaking File System\n");
    int ret1 = tfs_mkfs(file1, nBytes);

    if(ret1 < 0){
      printf("\t\tERROR MAKING FILE SYSTEM\n");
    }

    printf("\t\tMounting File System\n");
    int mount = tfs_mount(file1);

    if(mount < 0 ){
        printf("\t\tERROR MOUNTING FILE SYSTEM: %d\n", mount);
    }

    printf("\t\tOpening file %s on system %s\n", file2, file1);
    fileDescriptor fd = tfs_openFile(file2);

    if(fd < 0){
      printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd);
      return;
    }

    memset(buffer, 100, BLOCKSIZE);
    printf("\t\tWriting File to File System\n");
    int err = tfs_writeFile(fd, buffer, BLOCKSIZE);

    if(err < 0) {
       printf("\t\tERROR IN WRITE FILE: %d\n", err);
       return;
    }

    printf("\t\tWriting Byte to File\n");
    err = tfs_writeByte(fd, 5, 25);
    if( err < 0){
        printf("\t\tFAILED TO WRITE BYTE: %d\n", err);
        return;
    }

    printf("\t\tSeeking in File\n");
    err = tfs_seek(fd, 5);
    if( err < 0) {
        printf("\t\tFAILED TO SEEK: %d\n", err);
        return;
    }

    err = tfs_readByte(fd, &buffer);
    if(err < 0){
        printf("\t\tERROR IN READ BYTE: %d\n", error);
        return;
    }

    if(buffer != 25) {
        printf("\t\tERROR BUFFER VALUE: %u EXPECTED: %d\n", buffer, 25);
        return;
    }
    printf("\t\tSuccess\n");
    return;
}

void test_rename(){
	printf("\t Testing tfs_rename:\n");
    char file1[] = "tiny.txt";
    int nBytes = 10 * BLOCKSIZE;

    printf("\t\tMaking File System\n");
    int ret1 = tfs_mkfs(file1, nBytes);

    if(ret1 < 0){
      printf("\t\tERROR MAKING FILE SYSTEM\n");
    }

    printf("\t\tMOUNTING FILE SISYTEM\n");
    int mount = tfs_mount(file1);

    if(mount < 0 ){
        printf("\t\tERROR MOUNTING FILE SYSTEM\n");
    }

}

void test_readdir(){
    printf("\t Testing tfs_readdir:\n");
    char file1[] = "tiny.txt";
    char file2[] = "file1.txt";
    char file3[] = "file2.txt";
    char file4[] = "file3.txt";
    char buffer[] = "This is some data.";
    int nBytes = 10 * BLOCKSIZE;

    printf("\t\tMaking File System\n");
    int ret1 = tfs_mkfs(file1, nBytes);

    if(ret1 < 0){
        printf("\t\tERROR MAKING FILE SYSTEM\n");
    }

    printf("\t\tMounting File System\n");
    int mount = tfs_mount(file1);

    if(mount < 0){
        printf("\t\tERROR MOUNTING FILE SYSTEM: %d\n", mount);
    }

    printf("\t\tOpening file %s on system %s\n", file2, file1);
    fileDescriptor fd1 = tfs_openFile(file2);

    if(fd1 < 0){
        printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd1);
        return;
    }

    printf("\t\tWriting File to File System\n");
    int err = tfs_writeFile(fd1, buffer, 18);

    if(err < 0) {
       printf("\t\tERROR IN WRITE FILE: %d\n", err);
       return;
    }

    printf("\t\tOpening file %s on system %s\n", file3, file1);
    fileDescriptor fd2 = tfs_openFile(file3);

    if(fd2 < 0){
        printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd2);
        return;
    }

    printf("\t\tWriting File to File System\n");
    err = tfs_writeFile(fd2, buffer, 18);

    if(err < 0) {
        printf("\t\tERROR IN WRITE FILE: %d\n", err);
        return;
    }

    printf("\t\tOpening file %s on system %s\n", file4, file1);
    fileDescriptor fd3 = tfs_openFile(file4);

    if(fd3 < 0){
        printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd3);
        return;
    }

    printf("\t\tWriting File to File System\n");
    int err = tfs_writeFile(fd3, buffer, 18);

    if(err < 0) {
        printf("\t\tERROR IN WRITE FILE: %d\n", err);
        return;
    }
	
    printf("\t\tReading Directory\n");
    tfs_readdir();  
    printSuperBlock();
    printf("\t\tSuccess\n");
    return;
}

void test_readFileInfo(){
	printf("\t Testing tfs_readFileInfo:\n");
        
    char file1[] = "tiny.txt";
    char file2[] = "file.txt";
    int nBytes = 10 * BLOCKSIZE;

    printf("\t\tMaking File System\n");
    int ret1 = tfs_mkfs(file1, nBytes);

    if(ret1 < 0){
        printf("\t\tERROR MAKING FILE SYSTEM: %d\n", ret1);
        return;
    }

    printf("\t\tMounting File System\n");
    int mount = tfs_mount(file1);

    if(mount < 0 ){
        printf("\t\tERROR MOUNTING FILE SYSTEM: %d\n", mount);
        return;
    }
    printf("\t\tOpening file %s on system %s\n", file2, file1);

    fileDescriptor fd = tfs_openFile(file2);

    if(fd < 0){
        printf("\t\tFILE DESCRIPTOR INVALID: %d\n", fd);
        return;
    }
    tfs_readFileInfo(fd);
}

void test_makeRO(){
	printf("\t Testing tfs_makeRO:\n");

        char file1[] = "tiny.txt";
        int nBytes = 10 * BLOCKSIZE;

        printf("\t\tMaking File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);

        if(ret1 < 0){
          printf("\t\tERROR MAKING FILE SYSTEM\n");
        }

        printf("\t\tMOUNTING FILE SISYTEM\n");
        int mount = tfs_mount(file1);

        if(mount < 0 ){
            printf("\t\tERROR MOUNTING FILE SYSTEM\n");
        }

}

void test_makeRW(){
	printf("\t Testing tfs_makeRW:\n");
        char file1[] = "tiny.txt";
        int nBytes = 10 * BLOCKSIZE;

        printf("\t\tMaking File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);

        if(ret1 < 0){
          printf("\t\tERROR MAKING FILE SYSTEM\n");
        }

        printf("\t\tMOUNTING FILE SISYTEM\n");
        int mount = tfs_mount(file1);

        if(mount < 0 ){
            printf("\t\tERROR MOUNTING FILE SYSTEM\n");
        }

}

void test_writeByte(){
	printf("\t Testing tfs_writeByte:\n");

        char file1[] = "tiny.txt";
        int nBytes = 10 * BLOCKSIZE;

        printf("\t\tMaking File System\n");
        int ret1 = tfs_mkfs(file1, nBytes);

        if(ret1 < 0){
          printf("\t\tERROR MAKING FILE SYSTEM\n");
        }

        printf("\t\tMOUNTING FILE SISYTEM\n");
        int mount = tfs_mount(file1);

        if(mount < 0 ){
            printf("\t\tERROR MOUNTING FILE SYSTEM\n");
        }

}


int main(void){

	printf("Starting basic functionality testing: \n");

	//test_mkfs();
        //test_mount_unmount();
	//test_addFDtoSUP();
	//test_openFile_closeFile();
	test_writeFile();
	/*test_nextFD();
	test_deleteFile();
	test_readByte();
	test_seek();
	test_rename();
	test_readdir();
	test_readFileInfo();
	test_makeRO();
	test_makeRW();
	test_writeByte();*/

 	return 0;
}


