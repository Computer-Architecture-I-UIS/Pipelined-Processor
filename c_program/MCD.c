
void main()
{
	int * MCD = (int *) 0x20000060;
	int * N1 = (int *) 0x20000064;
	int * N2 = (int *) 0x20000068;
	int * Counteri = (int *) 0x2000006C;
	int * Counterk = (int *) 0x20002080;
	int * Counterl = (int *) 0x20002084;
	*MCD = 0;
	*N1 = 16;
	*N2 = 12;
	*Counteri = 1;
	*Counterk = *N1;
	*Counterl = *N2;
	
	if(*N1==*N2){*MCD = *N1;}
	else{ 
		while( *Counteri <= *N2 ){
			while(*Counterk >= *Counteri){
				*Counterk = *Counterk - *Counteri;
			}
			while(*Counterl >= *Counteri){
				*Counterl = *Counterl - *Counteri;
			}
			if(*Counterk == 0x0 ){ 
				if(*Counterl == 0x0){
					*MCD = *Counteri;
				} 
			}
			*Counterk = *N1;
			*Counterl = *N2;
			*Counteri = *Counteri + 1;
		}	
	}
}
