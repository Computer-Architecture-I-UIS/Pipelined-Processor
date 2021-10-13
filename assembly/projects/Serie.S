//
	addi x1, zero, 0
	addi x2, zero, 0
	addi x3, zero, 0
	addi x4, zero, 0
	addi x5, zero, 0
	addi x6, zero, 0
	addi x7, zero, 0
	addi x8, zero, 0
	addi x9, zero, 0
	addi x10, zero, 0
	addi x11, zero, 0
	addi x12, zero, 0
	addi x13, zero, 0
	addi x14, zero, 0
	addi x15, zero, 0
	addi x16, zero, 0
	addi x17, zero, 0
	addi x18, zero, 0
	addi x19, zero, 0
	addi x20, zero, 0
	addi x21, zero, 0
	addi x22, zero, 0
	addi x23, zero, 0
	addi x24, zero, 0
	addi x25, zero, 0
	addi x26, zero, 0
	addi x27, zero, 0
	addi x28, zero, 0
	addi x29, zero, 0
	addi x30, zero, 0
	addi x31, zero, 0

_start:
	li t0, 9; // Minimo valor Resultado final
	li t1, 63; //Primer digito
	li t2, 0; //Resultado intermedio
	li t3, 0; //Resultado final

	.begin:

		add t1, zero, 63;//Volvemos al mismo numero incial

		add t2, zero ,0 ;//Volvemos al mismo numero incial
		add t3, zero ,0 ;//Volvemos al mismo numero incial
		

		.CalSerie://Bucle que permite generar la serie
			
				
			sub t2, t1, t0;//Restando 9
			add t3, t2, 5;//Sumando los dos digitos
			
			add t1, t3, 0;
			
			blt t1, t0, .begin

			jal x1, .CalSerie//Saltamos a .CalSerie

j _start
