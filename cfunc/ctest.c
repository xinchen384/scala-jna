


/* ctest.c */


#include <stdio.h>

// to compile
// gcc -o libctest.so -shared ctest.c -fPIC
void helloFromC() {
    printf("Hello from C!\n");
}


void getFile(){

   FILE *fp;
   fp = fopen("/tmp/test.txt", "w+");
   fprintf(fp, "This is testing for fprintf...\n");
   fputs("This is testing for fputs...\n", fp);
   fclose(fp);
}
