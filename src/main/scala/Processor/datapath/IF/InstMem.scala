package Processor

import chisel3._
import chisel3.util._

class InstMem extends Module{
	val io = IO(new Bundle {
		val addrI = Input(UInt(32.W))
		val instruc = Output(UInt(32.W))
})

io.instruc:=0.U

/*
 addi x1,x0,1
 addi x1,x0,1
 addi x2,x0,2
*/

	when (io.addrI === 0.U){ io.instruc := "h00000000".U }
	.elsewhen (io.addrI === 1.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 2.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 3.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 4.U){ io.instruc := "h00000093".U }
	.elsewhen (io.addrI === 5.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 6.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 7.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 8.U){ io.instruc := "h00000113".U }
	.elsewhen (io.addrI === 9.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 10.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 11.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 12.U){ io.instruc := "h00000193".U }
	.elsewhen (io.addrI === 13.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 14.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 15.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 16.U){ io.instruc := "h00000213".U }
	.elsewhen (io.addrI === 17.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 18.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 19.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 20.U){ io.instruc := "h00000293".U }
	.elsewhen (io.addrI === 21.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 22.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 23.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 24.U){ io.instruc := "h00000313".U }
	.elsewhen (io.addrI === 25.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 26.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 27.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 28.U){ io.instruc := "h00000393".U }
	.elsewhen (io.addrI === 29.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 30.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 31.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 32.U){ io.instruc := "h00000413".U }
	.elsewhen (io.addrI === 33.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 34.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 35.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 36.U){ io.instruc := "h00000493".U }
	.elsewhen (io.addrI === 37.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 38.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 39.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 40.U){ io.instruc := "h00000513".U }
	.elsewhen (io.addrI === 41.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 42.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 43.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 44.U){ io.instruc := "h00000593".U }
	.elsewhen (io.addrI === 45.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 46.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 47.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 48.U){ io.instruc := "h00000613".U }
	.elsewhen (io.addrI === 49.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 50.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 51.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 52.U){ io.instruc := "h00000693".U }
	.elsewhen (io.addrI === 53.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 54.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 55.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 56.U){ io.instruc := "h00000713".U }
	.elsewhen (io.addrI === 57.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 58.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 59.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 60.U){ io.instruc := "h00000793".U }
	.elsewhen (io.addrI === 61.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 62.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 63.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 64.U){ io.instruc := "h00000813".U }
	.elsewhen (io.addrI === 65.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 66.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 67.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 68.U){ io.instruc := "h00000893".U }
	.elsewhen (io.addrI === 69.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 70.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 71.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 72.U){ io.instruc := "h00000913".U }
	.elsewhen (io.addrI === 73.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 74.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 75.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 76.U){ io.instruc := "h00000993".U }
	.elsewhen (io.addrI === 77.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 78.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 79.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 80.U){ io.instruc := "h00000a13".U }
	.elsewhen (io.addrI === 81.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 82.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 83.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 84.U){ io.instruc := "h00000a93".U }
	.elsewhen (io.addrI === 85.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 86.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 87.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 88.U){ io.instruc := "h00000b13".U }
	.elsewhen (io.addrI === 89.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 90.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 91.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 92.U){ io.instruc := "h00000b93".U }
	.elsewhen (io.addrI === 93.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 94.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 95.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 96.U){ io.instruc := "h00000c13".U }
	.elsewhen (io.addrI === 97.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 98.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 99.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 100.U){ io.instruc := "h00000c93".U }
	.elsewhen (io.addrI === 101.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 102.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 103.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 104.U){ io.instruc := "h00000d13".U }
	.elsewhen (io.addrI === 105.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 106.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 107.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 108.U){ io.instruc := "h00000d93".U }
	.elsewhen (io.addrI === 109.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 110.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 111.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 112.U){ io.instruc := "h00000e13".U }
	.elsewhen (io.addrI === 113.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 114.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 115.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 116.U){ io.instruc := "h00000e93".U }
	.elsewhen (io.addrI === 117.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 118.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 119.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 120.U){ io.instruc := "h00000f13".U }
	.elsewhen (io.addrI === 121.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 122.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 123.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 124.U){ io.instruc := "h00000f93".U }
	.elsewhen (io.addrI === 125.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 126.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 127.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 128.U){ io.instruc := "h02500313".U }
	.elsewhen (io.addrI === 129.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 130.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 131.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 132.U){ io.instruc := "h00100293".U }
	.elsewhen (io.addrI === 133.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 134.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 135.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 136.U){ io.instruc := "h00137393".U }
	.elsewhen (io.addrI === 137.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 138.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 139.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 140.U){ io.instruc := "h00538663".U }
	.elsewhen (io.addrI === 141.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 142.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 143.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 144.U){ io.instruc := "h00135313".U }
	.elsewhen (io.addrI === 145.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 146.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 147.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 148.U){ io.instruc := "hff5ff0ef".U }
	.elsewhen (io.addrI === 149.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 150.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 151.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 152.U){ io.instruc := "h00630e33".U }
	.elsewhen (io.addrI === 153.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 154.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 155.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 156.U){ io.instruc := "h006e0e33".U }
	.elsewhen (io.addrI === 157.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 158.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 159.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 160.U){ io.instruc := "h001e0313".U }
	.elsewhen (io.addrI === 161.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 162.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 163.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 164.U){ io.instruc := "hfe5ff0ef".U }
	.elsewhen (io.addrI === 165.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 166.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 167.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 168.U){ io.instruc := "hfd9ff06f".U }
	.elsewhen (io.addrI === 169.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 170.U){ io.instruc := "h00000013".U }
	.elsewhen (io.addrI === 171.U){ io.instruc := "h00000013".U }


}


