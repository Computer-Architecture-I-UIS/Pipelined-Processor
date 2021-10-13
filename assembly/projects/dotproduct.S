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

.data
arr1: .word 1, 2, 3, 4, 6, 9, 5  
arr2: .word 4, 9, 6, 3, 5, 8, 3 
len:  .word 7       # array length = 7


.text
# s1 = arr1 base address
# s2 = arr2 base address
# s3 = array length
# s4 = sum
# t0 = i
# t1 = a[i]
# t2 = b[i]
# t3 = a[i] * b[i] (assume no overflow, lower 32 bits)

main:
    la  s1, arr1      # s1 = a
    la  s2, arr2      # s2 = b
    lw  s3, len       # s3 = 3
    add s4, x0, x0    # sum = 0
    add t0, x0, x0    # i = 0
    
loop:
    lw   t1, 0(s1)    # t1 = a[i]
    addi s1, s1, 4    # ++a (address move forward)
    lw   t2, 0(s2)    # t2 = b[i]
    addi s2, s2, 4    # ++b
    add t4, x0, 1     # t4 = 1   
    add t3, t1, x0    # t3 = t1
    
multi:     
    add t3, t3, t1    # 
    add t4, t4, 1     # t4 = t4 + 1
    blt t4, t2, multi # if t4 < t2 go to multi
    
    add  s4, s4, t3   # sum += a[i] * b[i]
    addi t0, t0, 1    # ++i
    blt  t0, s3, loop # if i < len, go to loop






