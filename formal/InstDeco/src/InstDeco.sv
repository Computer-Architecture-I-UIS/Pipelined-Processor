module InstDeco(
  input         clock,
  input         reset,
  input  [31:0] io_instruc,
  output [4:0]  io_rd,
  output [4:0]  io_rs1,
  output [4:0]  io_rs2,
  output [31:0] io_imm,
  output [5:0]  io_state
);
`ifdef RANDOMIZE_REG_INIT
  reg [31:0] _RAND_0;
`endif // RANDOMIZE_REG_INIT
  wire [6:0] opcode = io_instruc[6:0]; // @[InstDeco.scala 50:27]
  wire [2:0] funct3 = io_instruc[14:12]; // @[InstDeco.scala 52:27]
  wire [6:0] funct7 = io_instruc[31:25]; // @[InstDeco.scala 55:27]
  wire  _T_7 = 7'h37 == opcode; // @[Conditional.scala 37:30]
  wire [19:0] hi = io_instruc[31:12]; // @[InstDeco.scala 74:56]
  wire [31:0] _T_9 = {hi,12'h0}; // @[InstDeco.scala 74:76]
  wire  _T_10 = 7'h17 == opcode; // @[Conditional.scala 37:30]
  wire  _T_13 = 7'h6f == opcode; // @[Conditional.scala 37:30]
  wire  hi_hi_hi = io_instruc[31]; // @[InstDeco.scala 83:56]
  wire [7:0] hi_hi_lo = io_instruc[19:12]; // @[InstDeco.scala 83:71]
  wire  hi_lo = io_instruc[20]; // @[InstDeco.scala 83:89]
  wire [9:0] lo_hi = io_instruc[30:21]; // @[InstDeco.scala 83:104]
  wire [20:0] _T_15 = {hi_hi_hi,hi_hi_lo,hi_lo,lo_hi,1'h0}; // @[InstDeco.scala 83:123]
  wire  _T_16 = 7'h67 == opcode; // @[Conditional.scala 37:30]
  wire [11:0] _T_18 = io_instruc[31:20]; // @[InstDeco.scala 88:61]
  wire  _T_19 = 7'h63 == opcode; // @[Conditional.scala 37:30]
  wire  hi_hi_lo_1 = io_instruc[7]; // @[InstDeco.scala 93:71]
  wire [5:0] hi_lo_1 = io_instruc[30:25]; // @[InstDeco.scala 93:85]
  wire [3:0] lo_hi_1 = io_instruc[11:8]; // @[InstDeco.scala 93:103]
  wire [12:0] _T_21 = {hi_hi_hi,hi_hi_lo_1,hi_lo_1,lo_hi_1,1'h0}; // @[InstDeco.scala 93:121]
  wire  _T_22 = 3'h0 == funct3; // @[Conditional.scala 37:30]
  wire  _T_23 = 3'h1 == funct3; // @[Conditional.scala 37:30]
  wire  _T_24 = 3'h4 == funct3; // @[Conditional.scala 37:30]
  wire  _T_25 = 3'h5 == funct3; // @[Conditional.scala 37:30]
  wire  _T_26 = 3'h6 == funct3; // @[Conditional.scala 37:30]
  wire  _T_27 = 3'h7 == funct3; // @[Conditional.scala 37:30]
  wire [5:0] _GEN_0 = _T_27 ? 6'h9 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 113:58 InstDeco.scala 94:41]
  wire [5:0] _GEN_1 = _T_26 ? 6'h8 : _GEN_0; // @[Conditional.scala 39:67 InstDeco.scala 110:57]
  wire [5:0] _GEN_2 = _T_25 ? 6'h7 : _GEN_1; // @[Conditional.scala 39:67 InstDeco.scala 107:57]
  wire [5:0] _GEN_3 = _T_24 ? 6'h6 : _GEN_2; // @[Conditional.scala 39:67 InstDeco.scala 104:57]
  wire [5:0] _GEN_4 = _T_23 ? 6'h5 : _GEN_3; // @[Conditional.scala 39:67 InstDeco.scala 101:57]
  wire [5:0] _GEN_5 = _T_22 ? 6'h4 : _GEN_4; // @[Conditional.scala 40:58 InstDeco.scala 98:57]
  wire  _T_28 = 7'h3 == opcode; // @[Conditional.scala 37:30]
  wire  _T_33 = 3'h2 == funct3; // @[Conditional.scala 37:30]
  wire [5:0] _GEN_6 = _T_25 ? 6'h12 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 135:57 InstDeco.scala 119:41]
  wire [5:0] _GEN_7 = _T_24 ? 6'h11 : _GEN_6; // @[Conditional.scala 39:67 InstDeco.scala 132:57]
  wire [5:0] _GEN_8 = _T_33 ? 6'h10 : _GEN_7; // @[Conditional.scala 39:67 InstDeco.scala 129:57]
  wire [5:0] _GEN_9 = _T_23 ? 6'hf : _GEN_8; // @[Conditional.scala 39:67 InstDeco.scala 126:57]
  wire [5:0] _GEN_10 = _T_22 ? 6'he : _GEN_9; // @[Conditional.scala 40:58 InstDeco.scala 123:57]
  wire  _T_36 = 7'h23 == opcode; // @[Conditional.scala 37:30]
  wire [11:0] _T_38 = {funct7,io_instruc[11:7]}; // @[InstDeco.scala 140:83]
  wire [5:0] _GEN_11 = _T_33 ? 6'hc : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 151:57 InstDeco.scala 141:41]
  wire [5:0] _GEN_12 = _T_23 ? 6'hb : _GEN_11; // @[Conditional.scala 39:67 InstDeco.scala 148:57]
  wire [5:0] _GEN_13 = _T_22 ? 6'ha : _GEN_12; // @[Conditional.scala 40:58 InstDeco.scala 145:57]
  wire  _T_42 = 7'h13 == opcode; // @[Conditional.scala 37:30]
  wire  _T_47 = 3'h3 == funct3; // @[Conditional.scala 37:30]
  wire [4:0] _T_53 = io_instruc[24:20]; // @[InstDeco.scala 181:77]
  wire  _T_57 = 7'h0 == funct7; // @[Conditional.scala 37:30]
  wire  _T_58 = 7'h20 == funct7; // @[Conditional.scala 37:30]
  wire [5:0] _GEN_14 = _T_58 ? 6'h1b : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 193:81 InstDeco.scala 186:65]
  wire [5:0] _GEN_15 = _T_57 ? 6'h1a : _GEN_14; // @[Conditional.scala 40:58 InstDeco.scala 190:81]
  wire [11:0] _GEN_16 = _T_25 ? $signed({{7{_T_53[4]}},_T_53}) : $signed(_T_18); // @[Conditional.scala 39:67 InstDeco.scala 185:63 InstDeco.scala 156:39]
  wire [5:0] _GEN_17 = _T_25 ? _GEN_15 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 157:41]
  wire [11:0] _GEN_18 = _T_23 ? $signed({{7{_T_53[4]}},_T_53}) : $signed(_GEN_16); // @[Conditional.scala 39:67 InstDeco.scala 181:55]
  wire [5:0] _GEN_19 = _T_23 ? 6'h19 : _GEN_17; // @[Conditional.scala 39:67 InstDeco.scala 182:57]
  wire [5:0] _GEN_20 = _T_27 ? 6'h18 : _GEN_19; // @[Conditional.scala 39:67 InstDeco.scala 178:57]
  wire [11:0] _GEN_21 = _T_27 ? $signed(_T_18) : $signed(_GEN_18); // @[Conditional.scala 39:67 InstDeco.scala 156:39]
  wire [5:0] _GEN_22 = _T_26 ? 6'h17 : _GEN_20; // @[Conditional.scala 39:67 InstDeco.scala 175:57]
  wire [11:0] _GEN_23 = _T_26 ? $signed(_T_18) : $signed(_GEN_21); // @[Conditional.scala 39:67 InstDeco.scala 156:39]
  wire [5:0] _GEN_24 = _T_24 ? 6'h16 : _GEN_22; // @[Conditional.scala 39:67 InstDeco.scala 172:57]
  wire [11:0] _GEN_25 = _T_24 ? $signed(_T_18) : $signed(_GEN_23); // @[Conditional.scala 39:67 InstDeco.scala 156:39]
  wire [5:0] _GEN_26 = _T_47 ? 6'h15 : _GEN_24; // @[Conditional.scala 39:67 InstDeco.scala 169:57]
  wire [11:0] _GEN_27 = _T_47 ? $signed(_T_18) : $signed(_GEN_25); // @[Conditional.scala 39:67 InstDeco.scala 156:39]
  wire [5:0] _GEN_28 = _T_33 ? 6'h14 : _GEN_26; // @[Conditional.scala 39:67 InstDeco.scala 166:57]
  wire [11:0] _GEN_29 = _T_33 ? $signed(_T_18) : $signed(_GEN_27); // @[Conditional.scala 39:67 InstDeco.scala 156:39]
  wire [5:0] _GEN_30 = _T_22 ? 6'h13 : _GEN_28; // @[Conditional.scala 40:58 InstDeco.scala 163:57]
  wire [11:0] _GEN_31 = _T_22 ? $signed(_T_18) : $signed(_GEN_29); // @[Conditional.scala 40:58 InstDeco.scala 156:39]
  wire  _T_59 = 7'h33 == opcode; // @[Conditional.scala 37:30]
  wire  _T_62 = 7'h1 == funct7; // @[Conditional.scala 37:30]
  wire [5:0] _GEN_32 = _T_58 ? 6'h25 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 214:73 InstDeco.scala 204:65]
  wire [5:0] _GEN_33 = _T_62 ? 6'h24 : _GEN_32; // @[Conditional.scala 39:67 InstDeco.scala 211:73]
  wire [5:0] _GEN_34 = _T_57 ? 6'h23 : _GEN_33; // @[Conditional.scala 40:58 InstDeco.scala 208:73]
  wire [5:0] _GEN_35 = _T_62 ? 6'h27 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 226:73 InstDeco.scala 219:65]
  wire [5:0] _GEN_36 = _T_57 ? 6'h26 : _GEN_35; // @[Conditional.scala 40:58 InstDeco.scala 223:73]
  wire [5:0] _GEN_37 = _T_62 ? 6'h29 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 238:73 InstDeco.scala 231:65]
  wire [5:0] _GEN_38 = _T_57 ? 6'h28 : _GEN_37; // @[Conditional.scala 40:58 InstDeco.scala 235:73]
  wire [5:0] _GEN_39 = _T_62 ? 6'h2b : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 250:73 InstDeco.scala 243:65]
  wire [5:0] _GEN_40 = _T_57 ? 6'h2a : _GEN_39; // @[Conditional.scala 40:58 InstDeco.scala 247:73]
  wire [5:0] _GEN_41 = _T_62 ? 6'h2d : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 263:73 InstDeco.scala 256:65]
  wire [5:0] _GEN_42 = _T_57 ? 6'h2c : _GEN_41; // @[Conditional.scala 40:58 InstDeco.scala 260:73]
  wire [5:0] _GEN_43 = _T_58 ? 6'h30 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 278:73 InstDeco.scala 268:65]
  wire [5:0] _GEN_44 = _T_62 ? 6'h2f : _GEN_43; // @[Conditional.scala 39:67 InstDeco.scala 275:73]
  wire [5:0] _GEN_45 = _T_57 ? 6'h2e : _GEN_44; // @[Conditional.scala 40:58 InstDeco.scala 272:73]
  wire [5:0] _GEN_46 = _T_62 ? 6'h32 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 291:73 InstDeco.scala 284:65]
  wire [5:0] _GEN_47 = _T_57 ? 6'h31 : _GEN_46; // @[Conditional.scala 40:58 InstDeco.scala 288:73]
  wire [5:0] _GEN_48 = _T_62 ? 6'h34 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 306:73 InstDeco.scala 299:65]
  wire [5:0] _GEN_49 = _T_57 ? 6'h33 : _GEN_48; // @[Conditional.scala 40:58 InstDeco.scala 303:73]
  wire [5:0] _GEN_50 = _T_27 ? _GEN_49 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 200:41]
  wire [5:0] _GEN_51 = _T_26 ? _GEN_47 : _GEN_50; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_52 = _T_25 ? _GEN_45 : _GEN_51; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_53 = _T_24 ? _GEN_42 : _GEN_52; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_54 = _T_47 ? _GEN_40 : _GEN_53; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_55 = _T_33 ? _GEN_38 : _GEN_54; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_56 = _T_23 ? _GEN_36 : _GEN_55; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_57 = _T_22 ? _GEN_34 : _GEN_56; // @[Conditional.scala 40:58]
  wire  _T_86 = 7'h73 == opcode; // @[Conditional.scala 37:30]
  wire [31:0] _T_88 = {20'h0,io_instruc[31:20]}; // @[InstDeco.scala 314:68]
  wire [5:0] _GEN_58 = _T_27 ? 6'h22 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 337:57 InstDeco.scala 315:33]
  wire [5:0] _GEN_59 = _T_26 ? 6'h21 : _GEN_58; // @[Conditional.scala 39:67 InstDeco.scala 334:57]
  wire [5:0] _GEN_60 = _T_25 ? 6'h20 : _GEN_59; // @[Conditional.scala 39:67 InstDeco.scala 331:57]
  wire [5:0] _GEN_61 = _T_47 ? 6'h1f : _GEN_60; // @[Conditional.scala 39:67 InstDeco.scala 328:57]
  wire [5:0] _GEN_62 = _T_33 ? 6'h1e : _GEN_61; // @[Conditional.scala 39:67 InstDeco.scala 325:57]
  wire [5:0] _GEN_63 = _T_23 ? 6'h1d : _GEN_62; // @[Conditional.scala 39:67 InstDeco.scala 322:57]
  wire [5:0] _GEN_64 = _T_22 ? 6'h1c : _GEN_63; // @[Conditional.scala 40:58 InstDeco.scala 319:57]
  wire [31:0] _GEN_65 = _T_86 ? $signed(_T_88) : $signed(32'sh0); // @[Conditional.scala 39:67 InstDeco.scala 314:31 InstDeco.scala 66:15]
  wire [5:0] _GEN_66 = _T_86 ? _GEN_64 : 6'h0; // @[Conditional.scala 39:67 InstDeco.scala 57:17]
  wire [5:0] _GEN_67 = _T_59 ? _GEN_57 : _GEN_66; // @[Conditional.scala 39:67]
  wire [31:0] _GEN_68 = _T_59 ? $signed(32'sh0) : $signed(_GEN_65); // @[Conditional.scala 39:67 InstDeco.scala 66:15]
  wire [31:0] _GEN_69 = _T_42 ? $signed({{20{_GEN_31[11]}},_GEN_31}) : $signed(_GEN_68); // @[Conditional.scala 39:67]
  wire [5:0] _GEN_70 = _T_42 ? _GEN_30 : _GEN_67; // @[Conditional.scala 39:67]
  wire [31:0] _GEN_71 = _T_36 ? $signed({{20{_T_38[11]}},_T_38}) : $signed(_GEN_69); // @[Conditional.scala 39:67 InstDeco.scala 140:39]
  wire [5:0] _GEN_72 = _T_36 ? _GEN_13 : _GEN_70; // @[Conditional.scala 39:67]
  wire [31:0] _GEN_73 = _T_28 ? $signed({{20{_T_18[11]}},_T_18}) : $signed(_GEN_71); // @[Conditional.scala 39:67 InstDeco.scala 118:39]
  wire [5:0] _GEN_74 = _T_28 ? _GEN_10 : _GEN_72; // @[Conditional.scala 39:67]
  wire [31:0] _GEN_75 = _T_19 ? $signed({{19{_T_21[12]}},_T_21}) : $signed(_GEN_73); // @[Conditional.scala 39:67 InstDeco.scala 93:39]
  wire [5:0] _GEN_76 = _T_19 ? _GEN_5 : _GEN_74; // @[Conditional.scala 39:67]
  wire [5:0] _GEN_77 = _T_16 ? 6'hd : _GEN_76; // @[Conditional.scala 39:67 InstDeco.scala 87:41]
  wire [31:0] _GEN_78 = _T_16 ? $signed({{20{_T_18[11]}},_T_18}) : $signed(_GEN_75); // @[Conditional.scala 39:67 InstDeco.scala 88:39]
  wire [5:0] _GEN_79 = _T_13 ? 6'h3 : _GEN_77; // @[Conditional.scala 39:67 InstDeco.scala 82:41]
  wire [31:0] _GEN_80 = _T_13 ? $signed({{11{_T_15[20]}},_T_15}) : $signed(_GEN_78); // @[Conditional.scala 39:67 InstDeco.scala 83:39]
  wire [5:0] _GEN_81 = _T_10 ? 6'h2 : _GEN_79; // @[Conditional.scala 39:67 InstDeco.scala 77:41]
  wire [31:0] _GEN_82 = _T_10 ? $signed(_T_9) : $signed(_GEN_80); // @[Conditional.scala 39:67 InstDeco.scala 78:39]
  reg  REG; // @[InstDeco.scala 347:35]
  wire  _T_96 = ~REG; // @[InstDeco.scala 348:23]
  wire  _T_98 = opcode == 7'h63; // @[InstDeco.scala 353:36]
  wire  _T_99 = funct3 == 3'h0; // @[InstDeco.scala 353:60]
  wire  _T_100 = opcode == 7'h63 & funct3 == 3'h0; // @[InstDeco.scala 353:49]
  wire  _T_103 = funct3 == 3'h1; // @[InstDeco.scala 356:60]
  wire  _T_104 = _T_98 & funct3 == 3'h1; // @[InstDeco.scala 356:49]
  wire  _T_107 = funct3 == 3'h4; // @[InstDeco.scala 359:60]
  wire  _T_108 = _T_98 & funct3 == 3'h4; // @[InstDeco.scala 359:49]
  wire  _T_111 = funct3 == 3'h5; // @[InstDeco.scala 362:60]
  wire  _T_112 = _T_98 & funct3 == 3'h5; // @[InstDeco.scala 362:49]
  wire  _T_115 = funct3 == 3'h6; // @[InstDeco.scala 365:60]
  wire  _T_116 = _T_98 & funct3 == 3'h6; // @[InstDeco.scala 365:49]
  wire  _T_119 = funct3 == 3'h7; // @[InstDeco.scala 368:60]
  wire  _T_120 = _T_98 & funct3 == 3'h7; // @[InstDeco.scala 368:49]
  wire  _T_122 = opcode == 7'h3; // @[InstDeco.scala 371:36]
  wire  _T_124 = opcode == 7'h3 & _T_99; // @[InstDeco.scala 371:48]
  wire  _T_128 = _T_122 & _T_103; // @[InstDeco.scala 374:48]
  wire  _T_131 = funct3 == 3'h2; // @[InstDeco.scala 377:59]
  wire  _T_132 = _T_122 & funct3 == 3'h2; // @[InstDeco.scala 377:48]
  wire  _T_136 = _T_122 & _T_107; // @[InstDeco.scala 380:48]
  wire  _T_140 = _T_122 & _T_111; // @[InstDeco.scala 383:48]
  wire  _T_142 = opcode == 7'h23; // @[InstDeco.scala 386:36]
  wire  _T_144 = opcode == 7'h23 & _T_99; // @[InstDeco.scala 386:49]
  wire  _T_148 = _T_142 & _T_103; // @[InstDeco.scala 389:49]
  wire  _T_152 = _T_142 & _T_131; // @[InstDeco.scala 392:49]
  wire  _T_154 = opcode == 7'h13; // @[InstDeco.scala 395:36]
  wire  _T_156 = opcode == 7'h13 & _T_99; // @[InstDeco.scala 395:49]
  wire  _T_160 = _T_154 & _T_131; // @[InstDeco.scala 398:49]
  wire  _T_163 = funct3 == 3'h3; // @[InstDeco.scala 401:60]
  wire  _T_164 = _T_154 & funct3 == 3'h3; // @[InstDeco.scala 401:49]
  wire  _T_168 = _T_154 & _T_107; // @[InstDeco.scala 404:49]
  wire  _T_172 = _T_154 & _T_115; // @[InstDeco.scala 407:49]
  wire  _T_176 = _T_154 & _T_119; // @[InstDeco.scala 410:49]
  wire  _T_178 = opcode == 7'h73; // @[InstDeco.scala 413:36]
  wire  _T_180 = opcode == 7'h73 & _T_99; // @[InstDeco.scala 413:49]
  wire  _T_184 = _T_178 & _T_103; // @[InstDeco.scala 416:49]
  wire  _T_188 = _T_178 & _T_131; // @[InstDeco.scala 419:49]
  wire  _T_192 = _T_178 & _T_163; // @[InstDeco.scala 422:49]
  wire  _T_196 = _T_178 & _T_111; // @[InstDeco.scala 425:49]
  wire  _T_200 = _T_178 & _T_115; // @[InstDeco.scala 428:49]
  wire  _T_204 = _T_178 & _T_119; // @[InstDeco.scala 431:49]
  wire  _T_206 = opcode == 7'h67; // @[InstDeco.scala 434:35]
  wire [31:0] _GEN_86 = {{20{_T_18[11]}},_T_18}; // @[InstDeco.scala 435:80]
  wire  _T_212 = opcode == 7'h6f; // @[InstDeco.scala 437:35]
  wire [31:0] _GEN_87 = {{11{_T_15[20]}},_T_15}; // @[InstDeco.scala 438:79]
  wire  _T_218 = opcode == 7'h17; // @[InstDeco.scala 440:35]
  wire  _T_222 = $signed(io_imm) == $signed(_T_9); // @[InstDeco.scala 441:81]
  wire  _T_224 = opcode == 7'h37; // @[InstDeco.scala 443:35]
  wire  _GEN_85 = ~REG | REG; // @[InstDeco.scala 348:29 InstDeco.scala 351:30 InstDeco.scala 347:35]
  wire  _GEN_88 = ~_T_96; // @[InstDeco.scala 354:44]
  wire  _GEN_91 = _GEN_88 & ~_T_100; // @[InstDeco.scala 357:44]
  wire  _GEN_96 = _GEN_91 & ~_T_104; // @[InstDeco.scala 360:44]
  wire  _GEN_103 = _GEN_96 & ~_T_108; // @[InstDeco.scala 363:44]
  wire  _GEN_112 = _GEN_103 & ~_T_112; // @[InstDeco.scala 366:44]
  wire  _GEN_123 = _GEN_112 & ~_T_116; // @[InstDeco.scala 369:44]
  wire  _GEN_136 = _GEN_123 & ~_T_120; // @[InstDeco.scala 372:44]
  wire  _GEN_151 = _GEN_136 & ~_T_124; // @[InstDeco.scala 375:44]
  wire  _GEN_168 = _GEN_151 & ~_T_128; // @[InstDeco.scala 378:44]
  wire  _GEN_187 = _GEN_168 & ~_T_132; // @[InstDeco.scala 381:44]
  wire  _GEN_208 = _GEN_187 & ~_T_136; // @[InstDeco.scala 384:44]
  wire  _GEN_231 = _GEN_208 & ~_T_140; // @[InstDeco.scala 387:44]
  wire  _GEN_256 = _GEN_231 & ~_T_144; // @[InstDeco.scala 390:44]
  wire  _GEN_283 = _GEN_256 & ~_T_148; // @[InstDeco.scala 393:44]
  wire  _GEN_312 = _GEN_283 & ~_T_152; // @[InstDeco.scala 396:44]
  wire  _GEN_343 = _GEN_312 & ~_T_156; // @[InstDeco.scala 399:44]
  wire  _GEN_376 = _GEN_343 & ~_T_160; // @[InstDeco.scala 402:44]
  wire  _GEN_411 = _GEN_376 & ~_T_164; // @[InstDeco.scala 405:44]
  wire  _GEN_448 = _GEN_411 & ~_T_168; // @[InstDeco.scala 408:44]
  wire  _GEN_487 = _GEN_448 & ~_T_172; // @[InstDeco.scala 411:44]
  wire  _GEN_528 = _GEN_487 & ~_T_176; // @[InstDeco.scala 414:44]
  wire  _GEN_571 = _GEN_528 & ~_T_180; // @[InstDeco.scala 417:44]
  wire  _GEN_616 = _GEN_571 & ~_T_184; // @[InstDeco.scala 420:44]
  wire  _GEN_663 = _GEN_616 & ~_T_188; // @[InstDeco.scala 423:44]
  wire  _GEN_712 = _GEN_663 & ~_T_192; // @[InstDeco.scala 426:44]
  wire  _GEN_763 = _GEN_712 & ~_T_196; // @[InstDeco.scala 429:44]
  wire  _GEN_816 = _GEN_763 & ~_T_200; // @[InstDeco.scala 432:44]
  wire  _GEN_871 = _GEN_816 & ~_T_204; // @[InstDeco.scala 435:44]
  wire  _GEN_928 = _GEN_871 & ~_T_206; // @[InstDeco.scala 438:44]
  wire  _GEN_987 = _GEN_928 & ~_T_212; // @[InstDeco.scala 441:44]
  assign io_rd = io_instruc[11:7]; // @[InstDeco.scala 59:26]
  assign io_rs1 = io_instruc[19:15]; // @[InstDeco.scala 62:27]
  assign io_rs2 = io_instruc[24:20]; // @[InstDeco.scala 64:27]
  assign io_imm = _T_7 ? $signed(_T_9) : $signed(_GEN_82); // @[Conditional.scala 40:58 InstDeco.scala 74:39]
  assign io_state = _T_7 ? 6'h1 : _GEN_81; // @[Conditional.scala 40:58 InstDeco.scala 73:41]
  always @(posedge clock) begin
    if (reset) begin // @[InstDeco.scala 347:35]
      REG <= 1'h0; // @[InstDeco.scala 347:35]
    end else begin
      REG <= _GEN_85;
    end
  end
// Register and memory initialization
`ifdef RANDOMIZE_GARBAGE_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_INVALID_ASSIGN
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_REG_INIT
`define RANDOMIZE
`endif
`ifdef RANDOMIZE_MEM_INIT
`define RANDOMIZE
`endif
`ifndef RANDOM
`define RANDOM $random
`endif
`ifdef RANDOMIZE_MEM_INIT
  integer initvar;
`endif
`ifndef SYNTHESIS
`ifdef FIRRTL_BEFORE_INITIAL
`FIRRTL_BEFORE_INITIAL
`endif
initial begin
  `ifdef RANDOMIZE
    `ifdef INIT_RANDOM
      `INIT_RANDOM
    `endif
    `ifndef VERILATOR
      `ifdef RANDOMIZE_DELAY
        #`RANDOMIZE_DELAY begin end
      `else
        #0.002 begin end
      `endif
    `endif
`ifdef RANDOMIZE_REG_INIT
  _RAND_0 = {1{`RANDOM}};
  REG = _RAND_0[0:0];
`endif // RANDOMIZE_REG_INIT
  `endif // RANDOMIZE
end // initial
`ifdef FIRRTL_AFTER_INITIAL
`FIRRTL_AFTER_INITIAL
`endif
`endif // SYNTHESIS
  always @(posedge clock) begin
    //
    if (_T_96) begin
      assume(reset); // @[InstDeco.scala 349:44]
    end
    //
    if (~_T_96 & _T_100) begin
      assert(io_state == 6'h4); // @[InstDeco.scala 354:44]
    end
    //
    if (_GEN_88 & ~_T_100 & _T_104) begin
      assert(io_state == 6'h5); // @[InstDeco.scala 357:44]
    end
    //
    if (_GEN_91 & ~_T_104 & _T_108) begin
      assert(io_state == 6'h6); // @[InstDeco.scala 360:44]
    end
    //
    if (_GEN_96 & ~_T_108 & _T_112) begin
      assert(io_state == 6'h7); // @[InstDeco.scala 363:44]
    end
    //
    if (_GEN_103 & ~_T_112 & _T_116) begin
      assert(io_state == 6'h8); // @[InstDeco.scala 366:44]
    end
    //
    if (_GEN_112 & ~_T_116 & _T_120) begin
      assert(io_state == 6'h9); // @[InstDeco.scala 369:44]
    end
    //
    if (_GEN_123 & ~_T_120 & _T_124) begin
      assert(io_state == 6'he); // @[InstDeco.scala 372:44]
    end
    //
    if (_GEN_136 & ~_T_124 & _T_128) begin
      assert(io_state == 6'hf); // @[InstDeco.scala 375:44]
    end
    //
    if (_GEN_151 & ~_T_128 & _T_132) begin
      assert(io_state == 6'h10); // @[InstDeco.scala 378:44]
    end
    //
    if (_GEN_168 & ~_T_132 & _T_136) begin
      assert(io_state == 6'h11); // @[InstDeco.scala 381:44]
    end
    //
    if (_GEN_187 & ~_T_136 & _T_140) begin
      assert(io_state == 6'h12); // @[InstDeco.scala 384:44]
    end
    //
    if (_GEN_208 & ~_T_140 & _T_144) begin
      assert(io_state == 6'ha); // @[InstDeco.scala 387:44]
    end
    //
    if (_GEN_231 & ~_T_144 & _T_148) begin
      assert(io_state == 6'hb); // @[InstDeco.scala 390:44]
    end
    //
    if (_GEN_256 & ~_T_148 & _T_152) begin
      assert(io_state == 6'hc); // @[InstDeco.scala 393:44]
    end
    //
    if (_GEN_283 & ~_T_152 & _T_156) begin
      assert(io_state == 6'h13); // @[InstDeco.scala 396:44]
    end
    //
    if (_GEN_312 & ~_T_156 & _T_160) begin
      assert(io_state == 6'h14); // @[InstDeco.scala 399:44]
    end
    //
    if (_GEN_343 & ~_T_160 & _T_164) begin
      assert(io_state == 6'h15); // @[InstDeco.scala 402:44]
    end
    //
    if (_GEN_376 & ~_T_164 & _T_168) begin
      assert(io_state == 6'h16); // @[InstDeco.scala 405:44]
    end
    //
    if (_GEN_411 & ~_T_168 & _T_172) begin
      assert(io_state == 6'h17); // @[InstDeco.scala 408:44]
    end
    //
    if (_GEN_448 & ~_T_172 & _T_176) begin
      assert(io_state == 6'h18); // @[InstDeco.scala 411:44]
    end
    //
    if (_GEN_487 & ~_T_176 & _T_180) begin
      assert(io_state == 6'h1c); // @[InstDeco.scala 414:44]
    end
    //
    if (_GEN_528 & ~_T_180 & _T_184) begin
      assert(io_state == 6'h1d); // @[InstDeco.scala 417:44]
    end
    //
    if (_GEN_571 & ~_T_184 & _T_188) begin
      assert(io_state == 6'h1e); // @[InstDeco.scala 420:44]
    end
    //
    if (_GEN_616 & ~_T_188 & _T_192) begin
      assert(io_state == 6'h1f); // @[InstDeco.scala 423:44]
    end
    //
    if (_GEN_663 & ~_T_192 & _T_196) begin
      assert(io_state == 6'h20); // @[InstDeco.scala 426:44]
    end
    //
    if (_GEN_712 & ~_T_196 & _T_200) begin
      assert(io_state == 6'h21); // @[InstDeco.scala 429:44]
    end
    //
    if (_GEN_763 & ~_T_200 & _T_204) begin
      assert(io_state == 6'h22); // @[InstDeco.scala 432:44]
    end
    //
    if (_GEN_816 & ~_T_204 & _T_206) begin
      assert(io_state == 6'hd & $signed(io_imm) == $signed(_GEN_86)); // @[InstDeco.scala 435:44]
    end
    //
    if (_GEN_871 & ~_T_206 & _T_212) begin
      assert(io_state == 6'h3 & $signed(io_imm) == $signed(_GEN_87)); // @[InstDeco.scala 438:44]
    end
    //
    if (_GEN_928 & ~_T_212 & _T_218) begin
      assert(io_state == 6'h2 & $signed(io_imm) == $signed(_T_9)); // @[InstDeco.scala 441:44]
    end
    //
    if (_GEN_987 & ~_T_218 & _T_224) begin
      assert(io_state == 6'h1 & _T_222); // @[InstDeco.scala 444:44]
    end
  end
endmodule
