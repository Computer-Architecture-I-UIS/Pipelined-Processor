void main()
{
	int * PosH = (int *) 0x20000060;
	int * PosV = (int *) 0x20000064;
	int * hor = (int *) 0x20000068;
	int * ver = (int *) 0x2000006C;
	*PosH = 0;
	*PosV = 0;
	*hor = 2;
	*ver = 2;

	
	for(int i=-2;i<=(2);i++){
		
		if(i!=0){
		
			for(int j=-2;j<=(2);j++){
				if(j!=0){
					if((i<0 && j<0) || (i>=0 && j>=0)){
						if (i != j && *hor+i>0 && *hor+i<9 && *ver+j>0 && *ver+j<9){
						*PosH = *hor+i;
						*PosV = *ver+j;
						}
					}
					else{
						if (i != (-j) && *hor+i>0 && *hor+i<9 && *ver+j>0 && *ver+j<9){
						*PosH = *hor+i;
						*PosV = *ver+j;
						}	
					} 
				}
			}
		}
		
	}
	
	
}
