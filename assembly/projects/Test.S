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
	li t0, 0
	li t1, 10
	li t2, 2

	li t4, 1
	sll t0, t1, t2
	slli t0, t1, 4

	li t4, 2
	srl t0, t1, t2
	srli t0, t1, 4

	li t4, 3
	sra t0, t1, t2
	srai t0, t1, 4

	li t4, 4
	add t0, t1, t2
	addi t0, t1, 13

	li t4, 5
	sub t0, t1, t2
	sub t0, t2, t1

	li t4, 6
	xor t0, t1, t2
	xori t0, t1, 5

	li t4, 7
	and t0, t1, t2
	andi t0, t1, 8

	li t4, 8
	or t0, t1, t2
	ori t0, t1, 1

	li t4, 9
	slt t0, t1, t2
	slt t0, t2, t1
	slti t0, t1, 20

	li t4, 10
	sltu t0, t1, t2
	sltu t0, t2, t1
	sltiu t0, t1, 20

	li t4, 11
	lui t0, 1
	auipc t0, 0

jal x1, _start
