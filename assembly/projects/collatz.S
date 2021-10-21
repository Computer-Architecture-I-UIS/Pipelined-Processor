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
	nop // Temporary Workaround
	  li t1, 13; //Inicial
    li t0, 1;  //Guardar para comparcion
    .inicio:
	nop // Temporary Workaround

      and t2, t1, 1;//Combrobar si el primer bit es 1
      beq t2, t0, .impar;//Saltamos a .impar si el numero es impar

      .par:
	  nop // Temporary Workaround

        srl t1, t1, 1; //Divide en 2
        jal x1, .inicio//Saltamos a .inicio

        .impar:
		nop // Temporary Workaround

          add t3, t1, t1;
          add t3, t3, t1;
          add t1, t3, 1;

          jal x1, .inicio//Saltamos a .inicio

j _start
