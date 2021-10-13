
void main()
{
	int *contador = (int *)0x20000064;  //reg1
	int *n = (int *)0x20000060;   //reg0
	int *band = (int *)0x20000068;  //reg 2
	int *primo = (int *)0x20000020;  // gpio
	int *n2 = (int *)0x20002080;  //rego REGTEST2
	int *i = (int *)0x2000006C;    // reg3
	int *flag = (int *)0x20002084; // reg1 REGTEST2
	*contador = 0;
	*n = 7;
	*n2 = *n;
	*primo = 1;
	*i = 2;
	*flag = 0;
	while(*band == 0)
	{
		*flag = 0;
		*n2 = *n;
		while(*flag == 0)
		{
			*contador = (*n2) - (*i);
			*n2 = *contador;
			if(*contador < *i ){*flag = 1;}
			if(*contador == 0)
			{
				*flag = 1;
				*band = 1;
				*primo = 0;
			}
		}
		if(*i == (*n - 1)){*band = 1;}
		else{(*i)++;}
		*contador = 0;
	}
}