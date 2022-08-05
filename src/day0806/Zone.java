package day0806;
import java.io.*;
import java.util.*;
import javax.swing.Spring;

import day0806.QTCalc;

public class Zone {
	String zoneNum, zoneName, zoneUsage, zoneHC, Mode_night, Mode_we;
	double θi_h_set, θi_c_set, Δθi_NA, Fx, Fx_Floor, Fx_GWall, θs_c, θi_h_min, θe_min, θSUP_Wi;
	double twd_d, th_op_d_we, th_op_d, dwd_a;
	double zoneArea, zoneHeight;
	double qI_p, qI_fac, Cwirk_A;
	double VA_we, VA_wd, n50, e, f, Vmech_SUP_we, Vmech_SUP_wd, Vmech_ETA_we, Vmech_ETA_wd, ηV_mech, ηχV_mech, χi_c_set, χi_h_set, Vmech_SUP_z, Vmech_ETA_z, ρacp_a;
	List zoneWall = new ArrayList(); List zoneRoof = new ArrayList(); List zoneFloor = new ArrayList(); List zoneGWall = new ArrayList(); List zoneDoor = new ArrayList(); List zoneWin = new ArrayList(); List zoneCW = new ArrayList();	
	double Zone_HT_tot, ZoneWall_HT, ZoneRoof_HT, ZoneFloor_HT, ZoneGWall_HT, ZoneDoor_HT, ZoneWin_HT, ZoneCW_HT;
	double ZoneWall_HT_Di, ZoneWall_HT_Indi, ZoneRoof_HT_Di, ZoneRoof_HT_Indi, ZoneWin_HT_Di, ZoneWin_HT_Indi, ZoneDoor_HT_Di, ZoneDoor_HT_Indi;
	double Zone_HT_TB_tot, ZoneWall_HT_TB, ZoneRoof_HT_TB, ZoneFloor_HT_TB, ZoneGwall_HT_TB , ZoneWin_HT_TB, ZoneDoor_HT_TB, ZoneCW_HT_TB;
	double Zone_HV_tot[]= new double[2], Zone_HV_inf[]= new double[2], Zone_HV_win[]= new double[2], Zone_HV_z[]= new double[2], Zone_HV_mech[]= new double[2] ; //[비이용일/이용일] = [we/wd]=[0/1]
	double Zone_H_tot[]= new double[2]; //[비이용일/이용일] = [we/wd]=[0/1]
	double τ[]= new double[2]; //[비이용일/이용일] = [we/wd]=[0/1]
	double θe[] = new double[12];  double dmth[] = new double[12]; double dwe_mth[] = new double[12]; double dwd_mth[] = new double[12]; 
	double θi[][][] = new double[2][2][12];
	
	//[난방/냉방][비이용일/이용일][mth] = [h/c][we/wd][mth]=[0/1][0/1][12]
	//QT
	double QTsink_tot[][][]= new double[2][2][12]; double QTsink_Wall[][][]= new double[2][2][12]; double QTsink_Roof[][][]= new double[2][2][12]; double QTsink_Floor[][][]= new double[2][2][12]; double QTsink_GWall[][][]= new double[2][2][12]; double QTsink_Door[][][]= new double[2][2][12];  double QTsink_Win[][][]= new double[2][2][12]; double QTsink_CW[][][]= new double[2][2][12];
	double QTsource_tot[][][]= new double[2][2][12]; double QTsource_Wall[][][]= new double[2][2][12]; double QTsource_Roof[][][]= new double[2][2][12]; double QTsource_Floor[][][]= new double[2][2][12]; double QTsource_GWall[][][]= new double[2][2][12]; double QTsource_Door[][][]= new double[2][2][12];  double QTsource_Win[][][]= new double[2][2][12]; double QTsource_CW[][][]= new double[2][2][12];
	//QS
	double QSopsink_tot[][][]= new double[2][2][12]; double QSopsink_Wall[]= new double[12]; double QSopsink_Roof[]= new double[12]; double QSopsink_Door[]= new double[12]; double QSopsink_CW_p[]= new double[12]; 
	double QSopsource_tot[][][]= new double[2][2][12]; double QSopsource_Wall[]= new double[12]; double QSopsource_Roof[]= new double[12];  double QSopsource_Door[]= new double[12];  double QSopsource_CW_p[]= new double[12];      
	double QStr_tot[][][]  = new double[2][2][12]; double QStr_Win[][]  = new double[2][12]; double QStr_CW[][]  = new double[2][12]; 
	//QV
	double QVsink_tot[][][]= new double[2][2][12]; double QV_inf_sink[][][]= new double[2][2][12]; double QV_win_sink[][][]= new double[2][2][12]; double QV_z_sink[][][]= new double[2][2][12]; double QV_mech_sink[][][]= new double[2][2][12]; 
	double QVsource_tot[][][]= new double[2][2][12]; double QV_inf_source[][][]= new double[2][2][12]; double QV_win_source[][][]= new double[2][2][12]; double QV_z_source[][][]= new double[2][2][12]; double QV_mech_source[][][]= new double[2][2][12];
	//QI
	double QI_tot[][][]= new double[2][2][12]; double QI_L[][][]= new double[2][2][12]; double QI_P[]= new double[2]; double QI_fac[]= new double[2];
	//
	double Qsink[][][]= new double[2][2][12]; double Qsource[][][]= new double[2][2][12]; double γ[][][]= new double[2][2][12]; double a[][][]= new double[2][2][12];	double η[][][]= new double[2][2][12];	 double ΔQc_b[][][]=new double[2][2][12]; double  ΔQc_sink[][][]=new double[2][2][12];
	double Qhb_we_day[] = new double[12]; double Qhb_wd_day[] = new double[12]; double Qcb_we_day[] = new double[12]; double Qcb_wd_day[] = new double[12];
	double Qhb_mth[] = new double[12]; double Qcb_mth[] = new double[12]; double Qhb_we_mth[] = new double[12]; double Qhb_wd_mth[] = new double[12]; double Qcb_we_mth[] = new double[12]; double Qcb_wd_mth[] = new double[12];
	double Qhb_a, Qcb_a, Qhb_we_a, Qhb_wd_a, Qcb_we_a, Qcb_wd_a;
	
	
	Zone(){
	    
	    try // 외기온도 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\OutairTemperature.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		θe[n-1] = Double.parseDouble(token[1]); 
            		dmth[n-1]= Double.parseDouble(token[2]); 
            		}  n++;
            	}
           
            
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	    
	    try // 존 정보 가져오기
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\Zone.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		zoneNum = token[1];
            		zoneName = token[2];
            		zoneUsage = token[3];
            		zoneHC = token[4];
            		θi_h_set = Double.parseDouble(token[5]);
            		θi_c_set = Double.parseDouble(token[6]);
            		Δθi_NA = Double.parseDouble(token[7]);
            		Fx = 0.8;
            		Fx_Floor = Double.parseDouble(token[9]);
            		Fx_GWall = Double.parseDouble(token[10]);
            		θs_c = 18;
            		θi_h_min = Double.parseDouble(token[12]);
            		θe_min = -12;
            		θSUP_Wi =18;
            		Mode_night = token[15];
            		Mode_we = token[16];
            		twd_d = Double.parseDouble(token[17]);
            		th_op_d_we = 0;
            		th_op_d = Double.parseDouble(token[19]);
            		dwd_a = Double.parseDouble(token[20]);
            		zoneArea = Double.parseDouble(token[21]);
            		zoneHeight = Double.parseDouble(token[22]);
            		qI_p = Double.parseDouble(token[23]);
            		qI_fac = Double.parseDouble(token[24]);
            		Cwirk_A = Double.parseDouble(token[25]);
            		VA_we = Double.parseDouble(token[26]);
            		VA_wd = Double.parseDouble(token[27]);
            		n50 = Double.parseDouble(token[28]);
            		e = Double.parseDouble(token[29]);
            		f = Double.parseDouble(token[30]);
            		Vmech_SUP_we = Double.parseDouble(token[31]);
            		Vmech_SUP_wd = Double.parseDouble(token[32]);
            		Vmech_ETA_we = Double.parseDouble(token[33]);
            		Vmech_ETA_wd = Double.parseDouble(token[34]);
            		ηV_mech = Double.parseDouble(token[35]);
            		ηχV_mech = Double.parseDouble(token[36]);
            		χi_c_set = Double.parseDouble(token[37]);
            		χi_h_set = Double.parseDouble(token[38]);
            		Vmech_SUP_z = Double.parseDouble(token[39]);
            		Vmech_ETA_z = Double.parseDouble(token[40]);
            		ρacp_a = 0.34;

            		}  n++;
            	}          
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	    
	    
	    

	    try //존 외벽 정보 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\ZoneWall.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		Wall wall = new Wall(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[5]), token[3]);
            		zoneWall.add(wall);            		
            		
            		}  n++;
            	}
           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


	    try //존 지붕 정보 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\ZoneRoof.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		Roof roof = new Roof(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[5]), token[3]);
            		zoneRoof.add(roof);            		
            		
            		}  n++;
            	}
           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


	    try //존 바닥 정보 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\ZoneFloor.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		Floor floor = new Floor(Double.parseDouble(token[1]), Double.parseDouble(token[2]));
            		zoneFloor.add(floor);            		
            		
            		}  n++;
            	}
           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

	    try //존 지하벽 정보 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\ZoneGWall.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		GWall gwall = new GWall(Double.parseDouble(token[1]), Double.parseDouble(token[2]));
            		zoneGWall.add(gwall);            		
            		
            		}  n++;
            	}
           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
	    
	    try //존 문 정보 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\ZoneDoor.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		Door door = new Door(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[5]), token[3]);
            		zoneDoor.add(door);            		
            		
            		}  n++;
            	}
           
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }   
	    
	    
	    
	    
	   try //존 창호 정보 가져오기 
        {
	    	String line = "";
		    final String delimiter = ",";
            String filePath = "C:\\javalecture\\day0806\\ZoneWin.csv";
            FileReader fileReader = new FileReader(filePath);
            BufferedReader reader = new BufferedReader(fileReader);
    	    int n=0; //line개수 카운트 
            while ((line = reader.readLine()) != null) {
            	String[] token = line.split(delimiter);
            	if(n==0) { //첫번째 행 건너뛰기  
            	}
            	else { //두번째 행부터 계산 
            		Window win = new Window(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[3]), token[4], Double.parseDouble(token[6]), Double.parseDouble(token[7]), Double.parseDouble(token[8]), Double.parseDouble(token[9]), Double.parseDouble(token[10]));
            		zoneWin.add(win);                   		           		
            		}  n++;
            	}
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }	
	   
	   
	   try //존 커튼월 정보 가져오기 
	     {
		    	String line = "";
			    final String delimiter = ",";
	         String filePath = "C:\\javalecture\\day0806\\ZoneCW.csv";
	         FileReader fileReader = new FileReader(filePath);
	         BufferedReader reader = new BufferedReader(fileReader);
	 	    int n=0; //line개수 카운트 
	         while ((line = reader.readLine()) != null) {
	         	String[] token = line.split(delimiter);
	         	if(n==0) { //첫번째 행 건너뛰기  
	         	}
	         	else { //두번째 행부터 계산 
	         		CW cw = new CW(Double.parseDouble(token[1]), Double.parseDouble(token[2]), Double.parseDouble(token[3]), Double.parseDouble(token[4]), Double.parseDouble(token[5]), Double.parseDouble(token[6]), Double.parseDouble(token[7]), Double.parseDouble(token[8]), Double.parseDouble(token[9]), Double.parseDouble(token[10]), Double.parseDouble(token[11]), Double.parseDouble(token[12]), Double.parseDouble(token[13]), Double.parseDouble(token[14]), Double.parseDouble(token[15]), Double.parseDouble(token[16]), Double.parseDouble(token[17]));
	         		zoneCW.add(cw);                   		           		
	         		}  n++;
	         	}
	     }
	     catch (IOException e)
	     {
	         e.printStackTrace();
	     }	    		
	    
	   
	}

	 
	
	
	

	
	
	//******************************************************************************************************************************************************************************H계산 

	void ZoneHT() {
		//외벽 HT
		for(int i = 0; i< zoneWall.size();i++) {
			Wall zonewall = (Wall) zoneWall.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneWall_HT[] = new double[zoneWall.size()];
			zoneWall_HT[i]=htcalc.Calc(zonewall.Ueff(), zonewall.Area());			
			if(zonewall.DiIndi().equals("Direction")) {
				ZoneWall_HT_Di += zoneWall_HT[i];
				
			}else if(zonewall.DiIndi().equals("Indirection")) {
				ZoneWall_HT_Indi += zoneWall_HT[i];
			} ZoneWall_HT = ZoneWall_HT_Di +ZoneWall_HT_Indi;		
	      }
		//지붕 HT
		for(int i = 0; i< zoneRoof.size();i++) {
			Roof zoneroof = (Roof) zoneRoof.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneRoof_HT[] = new double[zoneRoof.size()];
			zoneRoof_HT[i]=htcalc.Calc(zoneroof.Ueff(), zoneroof.Area());			
			if(zoneroof.DiIndi().equals("Direction")) {
				ZoneRoof_HT_Di += zoneRoof_HT[i];
				
			}else if(zoneroof.DiIndi().equals("Indirection")) {
				ZoneRoof_HT_Indi += zoneRoof_HT[i];
			} ZoneRoof_HT = ZoneRoof_HT_Di +ZoneRoof_HT_Indi;		
	      }
		//바닥 HT
		for(int i = 0; i< zoneFloor.size();i++) {
			Floor zonefloor = (Floor) zoneFloor.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneFloor_HT[] = new double[zoneFloor.size()];
			zoneFloor_HT[i]=htcalc.Calc(zonefloor.Ueff(), zonefloor.Area());		
				ZoneFloor_HT += zoneFloor_HT[i];
	      } 

		//지하벽 HT
		for(int i = 0; i< zoneGWall.size();i++) {
			GWall zonegwall = (GWall) zoneGWall.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneGWall_HT[] = new double[zoneGWall.size()];
			zoneGWall_HT[i]=htcalc.Calc(zonegwall.Ueff(), zonegwall.Area());		
				ZoneGWall_HT += zoneGWall_HT[i];
	      } 


		//문 HT
		for(int i = 0; i< zoneDoor.size();i++) {
			Door zonedoor = (Door) zoneDoor.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneDoor_HT[] = new double[zoneDoor.size()];
			zoneDoor_HT[i]=htcalc.Calc(zonedoor.Ueff(), zonedoor.Area());			
			if(zonedoor.DiIndi().equals("Direction")) {
				ZoneDoor_HT_Di += zoneDoor_HT[i];
				
			}else if(zonedoor.DiIndi().equals("Indirection")) {
				ZoneDoor_HT_Indi += zoneDoor_HT[i];
			} ZoneDoor_HT = ZoneDoor_HT_Di +ZoneDoor_HT_Indi;		
	      }

		//창 HT
		for(int i = 0; i< zoneWin.size();i++) {
			Window zonewin = (Window) zoneWin.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneWin_HT[] = new double[zoneWin.size()];
			zoneWin_HT[i]=htcalc.Calc(zonewin.Uvalue()  , zonewin.Area());			
			if(zonewin.DiIndi().equals("Direction")) {
				ZoneWin_HT_Di += zoneWin_HT[i];				
			}else if(zonewin.DiIndi().equals("Indirection")) {
				ZoneWin_HT_Indi += zoneWin_HT[i];	
			}
			double zoneWin_HT_TB[] = new double[zoneWin.size()];
			zoneWin_HT_TB[i] = htcalc.Calc(zonewin.Uinst()  , zonewin.Area());	
			ZoneWin_HT_TB += zoneWin_HT_TB[i];				
	    }  ZoneWin_HT = ZoneWin_HT_Di +ZoneWin_HT_Indi;

		//커튼월 HT
		for(int i = 0; i< zoneCW.size();i++) {
			CW zonecw = (CW) zoneCW.get(i); //List를 class 객체로 변환 
			HTCalc htcalc= new HTCalc();
			double zoneCW_HT_g[] = new double[zoneCW.size()]; double zoneCW_HT_p[] = new double[zoneCW.size()]; double zoneCW_HT_d[] = new double[zoneCW.size()];
			zoneCW_HT_g[i]=htcalc.Calc(zonecw.Uvalue_g()  , zonecw.Area_g());		
			zoneCW_HT_p[i]=htcalc.Calc(zonecw.Uvalue_p()  , zonecw.Area_p());		
			zoneCW_HT_d[i]=htcalc.Calc(zonecw.Uvalue_d()  , zonecw.Area_d());					
			ZoneCW_HT += (zoneCW_HT_g[i] +zoneCW_HT_p[i]+zoneCW_HT_d[i]);		
			double zoneCW_HT_TB[] = new double[zoneCW.size()];
			zoneCW_HT_TB[i] = htcalc.Calc(zonecw.Uinst()  , zonecw.Area_tot());	
			ZoneCW_HT_TB += zoneCW_HT_TB[i];
	    }  	   
	    Zone_HT_TB_tot = ZoneWall_HT_TB + ZoneRoof_HT_TB + ZoneFloor_HT_TB  + ZoneGwall_HT_TB + ZoneWin_HT_TB + ZoneDoor_HT_TB + ZoneCW_HT_TB;			
		Zone_HT_tot = Zone_HT_TB_tot + ZoneWall_HT+ ZoneRoof_HT + ZoneFloor_HT + ZoneGWall_HT + ZoneWin_HT + ZoneDoor_HT + ZoneCW_HT;
	}

	//환기 HV계산 
		void ZoneHV() {	
				HVCalc hvcalc= new HVCalc();
				Zone_HV_mech[0]=hvcalc.HV_mech_Calc(Vmech_SUP_we, th_op_d_we, (zoneArea * zoneHeight));
				Zone_HV_mech[1]=hvcalc.HV_mech_Calc(Vmech_SUP_wd, th_op_d, (zoneArea * zoneHeight));
				Zone_HV_z[0]=hvcalc.HV_z_Calc(Vmech_SUP_we, Vmech_ETA_we, th_op_d_we, (zoneArea * zoneHeight));
				Zone_HV_z[1]=hvcalc.HV_z_Calc(Vmech_SUP_wd, Vmech_ETA_wd, th_op_d, (zoneArea * zoneHeight));
				Zone_HV_inf[0]=hvcalc.HV_inf_Calc(Vmech_SUP_we, Vmech_ETA_we, Vmech_SUP_z, Vmech_ETA_z, th_op_d_we, n50, (zoneArea * zoneHeight), e, f);
				Zone_HV_inf[1]=hvcalc.HV_inf_Calc(Vmech_SUP_wd, Vmech_ETA_wd, Vmech_SUP_z, Vmech_ETA_z, th_op_d, n50, (zoneArea * zoneHeight), e, f);
				Zone_HV_win[0]= 0.1 * (zoneArea * zoneHeight)*0.34;
				Zone_HV_win[1]=hvcalc.HV_win_Calc(Vmech_SUP_wd, Vmech_ETA_wd, Vmech_SUP_z, Vmech_ETA_z, th_op_d, twd_d, n50, (VA_wd/zoneHeight), (zoneArea * zoneHeight), e, f);
				Zone_HV_tot[0] = Zone_HV_mech[0] + Zone_HV_z[0] + Zone_HV_inf[0] + Zone_HV_win[0];
				Zone_HV_tot[1] = Zone_HV_mech[1] + Zone_HV_z[1] + Zone_HV_inf[1] + Zone_HV_win[1];
		} 
		//******************************************************************************************************************************************************************************H계산 끝
		
		//******************************************************************************************************************************************************************************θi계산 
	
		void Zoneτ() {
			Zone_H_tot[0] = Zone_HT_tot + Zone_HV_tot[0];
			Zone_H_tot[1] = Zone_HT_tot + Zone_HV_tot[1];			
			θiCalc calc = new θiCalc();
			τ[0] = calc.τ_Calc(Cwirk_A*zoneArea, Zone_H_tot[0]);
			τ[1] = calc.τ_Calc(Cwirk_A*zoneArea, Zone_H_tot[1]);
		}
		
		void Zoneθi() {		
			θiCalc calc = new θiCalc();		
			for(int mth = 0; mth<12; mth++) {
			//[hc][wewd][mth]	
			θi[0][0][mth] = calc.θihwe_Calc(τ[0], Mode_we, θe[mth],  θi_h_set, Δθi_NA);
			θi[0][1][mth] = calc.θihwd_Calc(τ[1], Mode_night, (24-th_op_d) , θe[mth],  θi_h_set, Δθi_NA);
			θi[1][0][mth] = calc.θic_Calc(θi_c_set);
			θi[1][1][mth] = calc.θic_Calc(θi_c_set);
			}
			
		}
	//******************************************************************************************************************************************************************************Qt계산 

	

			
	void ZoneQT() {
		double QTsink_Di_tot[][][] = new double[2][2][12]; double QTsink_Wall_Di[][][] = new double[2][2][12]; double QTsink_Roof_Di[][][] = new double[2][2][12]; double QTsink_Door_Di[][][] = new double[2][2][12]; double QTsink_Win_Di[][][] = new double[2][2][12];  double QTsink_CW[][][] = new double[2][2][12];
		double QTsink_Indi_tot[][][] = new double[2][2][12]; double QTsink_Wall_Indi[][][] = new double[2][2][12]; double QTsink_Roof_Indi[][][] = new double[2][2][12]; double QTsink_Door_Indi[][][] = new double[2][2][12]; double QTsink_Win_Indi[][][] = new double[2][2][12];   
		double QTsource_Di_tot[][][] = new double[2][2][12];  double QTsource_Wall_Di[][][] = new double[2][2][12]; double QTsource_Roof_Di[][][] = new double[2][2][12]; double QTsource_Door_Di[][][] = new double[2][2][12]; double QTsource_Win_Di[][][] = new double[2][2][12];  double QTsource_CW[][][] = new double[2][2][12];
		double QTsource_Indi_tot[][][] = new double[2][2][12]; double QTsource_Wall_Indi[][][] = new double[2][2][12]; double QTsource_Roof_Indi[][][] = new double[2][2][12]; double QTsource_Door_Indi[][][] = new double[2][2][12]; double QTsource_Win_Indi[][][] = new double[2][2][12];  
		double QTsink_TB[][][] = new double[2][2][12];
		double QTsource_TB[][][] = new double[2][2][12];
		QTCalc qtcalc= new QTCalc();
		
		
   for(int hc = 0; hc<= 1;hc++) {
	  for(int wewd = 0; wewd<= 1;wewd++) {
		for(int mth = 0; mth<= 11;mth++) {
			
			//직접외기 QT계산 
			if(θi[hc][wewd][mth]>=θe[mth]) {
			QTsink_Wall_Di[hc][wewd][mth] = qtcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], ZoneWall_HT_Di);
			QTsink_Roof_Di[hc][wewd][mth] = qtcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], ZoneRoof_HT_Di);
			QTsink_Door_Di[hc][wewd][mth] = qtcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], ZoneDoor_HT_Di);
			QTsink_Win_Di[hc][wewd][mth] = qtcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], ZoneWin_HT_Di);
			QTsink_CW[hc][wewd][mth] = qtcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], ZoneCW_HT);
			QTsink_TB[hc][wewd][mth] = qtcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], Zone_HT_TB_tot);
			QTsink_Di_tot[hc][wewd][mth] =QTsink_TB[hc][wewd][mth] + QTsink_Wall_Di[hc][wewd][mth] + QTsink_Roof_Di[hc][wewd][mth] + QTsink_Door_Di[hc][wewd][mth] + QTsink_Win_Di[hc][wewd][mth] + QTsink_CW[hc][wewd][mth];
			} else if (θi[hc][wewd][mth]<θe[mth]) {
			QTsource_Wall_Di[hc][wewd][mth] = qtcalc.Calc_source(θe[mth], θi[hc][wewd][mth], ZoneWall_HT_Di);	
			QTsource_Roof_Di[hc][wewd][mth] = qtcalc.Calc_source(θe[mth], θi[hc][wewd][mth], ZoneRoof_HT_Di);	
			QTsource_Door_Di[hc][wewd][mth] = qtcalc.Calc_source(θe[mth], θi[hc][wewd][mth], ZoneDoor_HT_Di);	
			QTsource_Win_Di[hc][wewd][mth] = qtcalc.Calc_source(θe[mth], θi[hc][wewd][mth], ZoneWin_HT_Di);		
			QTsource_CW[hc][wewd][mth] = qtcalc.Calc_source(θe[mth], θi[hc][wewd][mth], ZoneCW_HT);	
			QTsource_TB[hc][wewd][mth] = qtcalc.Calc_source(θe[mth], θi[hc][wewd][mth], Zone_HT_TB_tot);
			QTsource_Di_tot[hc][wewd][mth] = QTsource_TB[hc][wewd][mth] + QTsource_Wall_Di[hc][wewd][mth] + QTsource_Roof_Di[hc][wewd][mth] + QTsource_Door_Di[hc][wewd][mth] + QTsource_Win_Di[hc][wewd][mth] + QTsource_CW[hc][wewd][mth];			
			}
		  
			//간접외기 QT계산  
				double θu[][][] = new double[2][2][12];				
				θu[hc][wewd][mth] = θi[hc][wewd][mth] - Fx * (θi[hc][wewd][mth] -θe[mth]);		
				
				if(θi[hc][wewd][mth]>=θu[hc][wewd][mth]) {
				QTsink_Wall_Indi[hc][wewd][mth] = qtcalc.Calc_sink(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneWall_HT_Indi);
				QTsink_Roof_Indi[hc][wewd][mth] = qtcalc.Calc_sink(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneRoof_HT_Indi);
				QTsink_Door_Indi[hc][wewd][mth] = qtcalc.Calc_sink(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneDoor_HT_Indi);
				QTsink_Win_Indi[hc][wewd][mth] = qtcalc.Calc_sink(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneWin_HT_Indi);	
				QTsink_Indi_tot[hc][wewd][mth] =QTsink_Wall_Indi[hc][wewd][mth] + QTsink_Roof_Indi[hc][wewd][mth] + QTsink_Door_Indi[hc][wewd][mth] + QTsink_Win_Indi[hc][wewd][mth];		
				} else if (θi[hc][wewd][mth]<θu[hc][wewd][mth]) {
				QTsource_Wall_Indi[hc][wewd][mth] = qtcalc.Calc_source(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneWall_HT_Indi);	
				QTsource_Roof_Indi[hc][wewd][mth] = qtcalc.Calc_source(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneRoof_HT_Indi);	
				QTsource_Door_Indi[hc][wewd][mth] = qtcalc.Calc_source(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneDoor_HT_Indi);	
				QTsource_Win_Indi[hc][wewd][mth] = qtcalc.Calc_source(θu[hc][wewd][mth], θi[hc][wewd][mth], ZoneWin_HT_Indi);	
				QTsource_Indi_tot[hc][wewd][mth] =QTsource_Wall_Indi[hc][wewd][mth] + QTsource_Roof_Indi[hc][wewd][mth] + QTsource_Door_Indi[hc][wewd][mth] + QTsource_Win_Indi[hc][wewd][mth];				
				}

	       //바닥 QT계산  
				double θs_Floor[][][] = new double[2][2][12];		
				θs_Floor[0][wewd][mth] = θi[0][wewd][mth] - Fx_Floor * (θi[0][wewd][mth] -θe[mth]);	
				θs_Floor[1][wewd][mth] = θs_c;											
				if(θi[hc][wewd][mth]>=θs_Floor[hc][wewd][mth]) {
				QTsink_Floor[hc][wewd][mth] = qtcalc.Calc_sink(θs_Floor[hc][wewd][mth], θi[hc][wewd][mth], ZoneFloor_HT);		
				} else if (θi[hc][wewd][mth]<θs_Floor[hc][wewd][mth]) {
				QTsource_Floor[hc][wewd][mth] = qtcalc.Calc_source(θs_Floor[hc][wewd][mth], θi[hc][wewd][mth], ZoneFloor_HT);		
				}
				
	      //지하벽 QT계산    
				double θs_GWall[][][] = new double[2][2][12];				
				θs_GWall[0][wewd][mth] = θi[0][wewd][mth] - Fx_GWall * (θi[0][wewd][mth] -θe[mth]);	
				θs_GWall[1][wewd][mth] = θs_c;							
				if(θi[hc][wewd][mth]>=θs_GWall[hc][wewd][mth]) {
				QTsink_GWall[hc][wewd][mth] = qtcalc.Calc_sink(θs_GWall[hc][wewd][mth], θi[hc][wewd][mth], ZoneGWall_HT);		
				} else if (θi[hc][wewd][mth]<θs_GWall[hc][wewd][mth]) {
				QTsource_GWall[hc][wewd][mth] = qtcalc.Calc_source(θs_GWall[hc][wewd][mth], θi[hc][wewd][mth], ZoneGWall_HT);	
				}
				
	    // QT_tot계산
		QTsink_tot[hc][wewd][mth] = QTsink_Di_tot[hc][wewd][mth] + QTsink_Indi_tot[hc][wewd][mth] + QTsink_Floor[hc][wewd][mth] + QTsink_GWall[hc][wewd][mth];
		QTsource_tot[hc][wewd][mth] = QTsource_Di_tot[hc][wewd][mth] + QTsource_Indi_tot[hc][wewd][mth] + QTsource_Floor[hc][wewd][mth] + QTsource_GWall[hc][wewd][mth];
		 
			  }
			}
		  }
  
	}
	
	//******************************************************************************************************************************************************************************Qt계산끝
	
	
	
	//******************************************************************************************************************************************************************************불투명 Qs계산 
	
	void ZoneQSop(){
		// 외벽 일사 계산
		 try 
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneWall_Solar.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
           	    double zoneWalls_Is[][] = new double[zoneWall.size()][12];
           	    double zoneWalls_Qssink[][] = new double[zoneWall.size()][12];	
           	    double zoneWalls_Qssource[][] = new double[zoneWall.size()][12];		
           	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        Wall zonewall = (Wall) zoneWall.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneWalls_Is[i-1][mth] = Double.parseDouble(token[mth+1]);
	            			QSopCalc qsopcalc= new QSopCalc();
	    	    			if(zonewall.DiIndi().equals("Indirection")) {	//직접외기 벽만 일사 계산      
	    	    			}else {	
	    	    				if(0.5 * 4.5 * 10 >= zonewall.α() * zoneWalls_Is[i-1][mth]) {
	    	    					zoneWalls_Qssink[i-1][mth] = qsopcalc.Calc(zonewall.Ueff(), zonewall.Area(), zonewall.α(), zoneWalls_Is[i-1][mth]);
	    	    					zoneWalls_Qssource[i-1][mth] = 0;	    	    					
	    	    				}else {
	    	    					zoneWalls_Qssink[i-1][mth] = 0;
	    	    					zoneWalls_Qssource[i-1][mth] = qsopcalc.Calc(zonewall.Ueff(), zonewall.Area(), zonewall.α(), zoneWalls_Is[i-1][mth]);
	    	    				} 
	    	    			}
	    	    			QSopsink_Wall[mth] +=  zoneWalls_Qssink[i-1][mth];
	    					QSopsource_Wall[mth] +=  zoneWalls_Qssource[i-1][mth];
		            		}
	            		} 	
	            	} 
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 
		 //지붕 일사 계산
		 try 
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneRoof_Solar.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
        	    double zoneRoofs_Is[][] = new double[zoneRoof.size()][12];
        	    double zoneRoofs_Qssink[][] = new double[zoneRoof.size()][12];	
        	    double zoneRoofs_Qssource[][] = new double[zoneRoof.size()][12];		
        	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        Roof zoneroof = (Roof) zoneRoof.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneRoofs_Is[i-1][mth] = Double.parseDouble(token[mth+1]);
	            			QSopCalc qsopcalc= new QSopCalc();
	    	    			if(zoneroof.DiIndi().equals("Indirection")) {	//직접외기 벽만 일사 계산      
	    	    			}else {	
	    	    				if(0.5 * 4.5 * 10 >= zoneroof.α() * zoneRoofs_Is[i-1][mth]) {
	    	    					zoneRoofs_Qssink[i-1][mth] = qsopcalc.Calc(zoneroof.Ueff(), zoneroof.Area(), zoneroof.α(), zoneRoofs_Is[i-1][mth]);
	    	    					zoneRoofs_Qssource[i-1][mth] = 0;	    	    					
	    	    				}else {
	    	    					zoneRoofs_Qssink[i-1][mth] = 0;
	    	    					zoneRoofs_Qssource[i-1][mth] = qsopcalc.Calc(zoneroof.Ueff(), zoneroof.Area(), zoneroof.α(), zoneRoofs_Is[i-1][mth]);
	    	    				} 
	    	    			}
	    	    			QSopsink_Roof[mth] +=  zoneRoofs_Qssink[i-1][mth];
	    					QSopsource_Roof[mth] +=  zoneRoofs_Qssource[i-1][mth];
		            		}
	            		} 	
	            	} 
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }	
		 
			// 출입문 일사 계산
		 try 
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneDoor_Solar.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
           	    double zoneDoors_Is[][] = new double[zoneDoor.size()][12];
           	    double zoneDoors_Qssink[][] = new double[zoneDoor.size()][12];	
           	    double zoneDoors_Qssource[][] = new double[zoneDoor.size()][12];		
           	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        Door zonedoor = (Door) zoneDoor.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneDoors_Is[i-1][mth] = Double.parseDouble(token[mth+1]);
	            			QSopCalc qsopcalc= new QSopCalc();
	    	    			if(zonedoor.DiIndi().equals("Indirection")) {	//직접외기 벽만 일사 계산      
	    	    			}else {	
	    	    				if(0.5 * 4.5 * 10 >= zonedoor.α() * zoneDoors_Is[i-1][mth]) {
	    	    					zoneDoors_Qssink[i-1][mth] = qsopcalc.Calc(zonedoor.Ueff(), zonedoor.Area(), zonedoor.α(), zoneDoors_Is[i-1][mth]);
	    	    					zoneDoors_Qssource[i-1][mth] = 0;	    	    					
	    	    				}else {
	    	    					zoneDoors_Qssink[i-1][mth] = 0;
	    	    					zoneDoors_Qssource[i-1][mth] = qsopcalc.Calc(zonedoor.Ueff(), zonedoor.Area(), zonedoor.α(), zoneDoors_Is[i-1][mth]);
	    	    				} 
	    	    			}
	    	    			QSopsink_Door[mth] +=  zoneDoors_Qssink[i-1][mth];
	    					QSopsource_Door[mth] +=  zoneDoors_Qssource[i-1][mth];
		            		}
	            		} 	
	            	} 
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 
			// 커튼월 패널 일사 계산
		 try 
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneCW_Solar.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
           	    double zoneCWs_Is[][] = new double[zoneCW.size()][12];
           	    double zoneCWs_Qssink[][] = new double[zoneCW.size()][12];	
           	    double zoneCWs_Qssource[][] = new double[zoneCW.size()][12];		
           	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        CW zonecw = (CW) zoneCW.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneCWs_Is[i-1][mth] = Double.parseDouble(token[mth+1]);
	            			QSopCalc qsopcalc= new QSopCalc();	    	    			
	    	    				if(0.5 * 4.5 * 10 >= zonecw.α_p() * zoneCWs_Is[i-1][mth]) {
	    	    					zoneCWs_Qssink[i-1][mth] = qsopcalc.Calc(zonecw.Uvalue_p(), zonecw.Area_p(), zonecw.α_p(), zoneCWs_Is[i-1][mth]);
	    	    					zoneCWs_Qssource[i-1][mth] = 0;	    	    					
	    	    				}else {
	    	    					zoneCWs_Qssink[i-1][mth] = 0;
	    	    					zoneCWs_Qssource[i-1][mth] = qsopcalc.Calc(zonecw.Uvalue_p(), zonecw.Area_p(), zonecw.α_p(),  zoneCWs_Is[i-1][mth]);
	    	    				} 
	    	    			QSopsink_CW_p[mth] +=  zoneCWs_Qssink[i-1][mth];
	    					QSopsource_CW_p[mth] +=  zoneCWs_Qssource[i-1][mth];
		            		}
	            		} 	
	            	} 
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }

	for(int hc =0; hc<=1; hc++) {
			for(int wewd =0; wewd<=1; wewd++) {		 
 		          for(int mth = 0; mth< 12;mth++) {	 
		 QSopsink_tot[hc][wewd][mth] = QSopsink_Wall[mth] + QSopsink_Roof[mth] + QSopsink_Door[mth] + QSopsink_CW_p[mth];  
		 QSopsource_tot[hc][wewd][mth] = QSopsource_Wall[mth] + QSopsource_Roof[mth] + QSopsource_Door[mth] + QSopsource_CW_p[mth];  
 		          }		 
 		      }
 		} 		          
	}

	
	//******************************************************************************************************************************************************************************불투명 Qs계산끝

	//******************************************************************************************************************************************************************************투명 Qs계산 
	
	void ZoneQStr(){
		
		// 창 일사 계산 
		double zoneWins_Is[][] = new double[zoneWin.size()][12];
  	    double zoneWins_Fs[][] = new double[zoneWin.size()][12];
  	    double zoneWins_a[][] = new double[zoneWin.size()][12];
  	    double zoneWins_geff[][][] = new double[zoneWin.size()][2][12];
  	    double zoneWins_Qs[][][] = new double[zoneWin.size()][2][12];					
		 try //존의 창별 일사정보 가져오기
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneWin_Solar.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
          	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        Window zonewin = (Window) zoneWin.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneWins_Is[i-1][mth] = Double.parseDouble(token[mth+1]);
		            		}
	            		} 	
	            	}
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 try //존의 창별 음영정보 가져오기
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneWin_shadow.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
       	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        Window zonewin = (Window) zoneWin.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneWins_Fs[i-1][mth] = Double.parseDouble(token[mth+1]);
		            		}
	            		} 	
	            	}
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 try //존의 창별 가동계수정보 가져오기
	        {
	    	    String line = "";
			    final String delimiter = ",";
	            String filePath = "C:\\javalecture\\day0806\\ZoneWin_a.csv";
	            FileReader fileReader = new FileReader(filePath);
	            BufferedReader reader = new BufferedReader(fileReader);
    	    
	           for(int i = 0; (line = reader.readLine()) != null; i++) { 
				   
	            	String[] token = line.split(delimiter);
	            	if(i==0) { //첫번째 행 건너뛰기  
	            	}
	            	else { //두번째 행부터 계산 	  
	            	        Window zonewin = (Window) zoneWin.get(i-1);
		            		for(int mth = 0; mth< 12;mth++) {
	            			zoneWins_a[i-1][mth] = Double.parseDouble(token[mth+1]);
		            		}
	            		} 	
	            	}
	        }
	        catch (IOException e)
	        {
	            e.printStackTrace();
	        }
		 for(int i = 0; i<zoneWin.size(); i++) {        	   
			 Window zonewin = (Window) zoneWin.get(i);
	            	for(int mth = 0; mth< 12;mth++) {
	            		GeffCalc geffcalc= new GeffCalc();
	            		QStrCalc qstrcalc= new QStrCalc();
	    					zoneWins_geff[i][0][mth] = geffcalc.Calc(zonewin.g(),zoneWins_Fs[i][mth]); 	   
	    					zoneWins_geff[i][1][mth] = geffcalc.Calc(zonewin.g(),zoneWins_Fs[i][mth]); 	 				
	    					zoneWins_geff[i][1][mth] = geffcalc.Calc(zonewin.g(),zoneWins_Fs[i][mth],zonewin.gtot(), zoneWins_a[i][mth]);	    					 
	            		if(zonewin.DiIndi().equals("Indirection")) {	//직접외기 창만 일사 계산      
 	    			}else {	
 	    			       zoneWins_Qs[i][0][mth] = qstrcalc.Calc(zonewin.Ff(), zonewin.Area(),zoneWins_geff[i][0][mth], zoneWins_Is[i][mth]);	
 	    			       zoneWins_Qs[i][1][mth] = qstrcalc.Calc(zonewin.Ff(), zonewin.Area(),zoneWins_geff[i][1][mth], zoneWins_Is[i][mth]);	 
 	    			}
	            		QStr_Win[0][mth] += zoneWins_Qs[i][0][mth];  
	            		QStr_Win[1][mth] += zoneWins_Qs[i][1][mth];  
	            		}	            		
         		 	
         	}
		 

			
			// 커튼월 일사 계산 
			double zoneCWs_Is[][] = new double[zoneWin.size()][12];
	  	    double zoneCWs_Fs[][] = new double[zoneWin.size()][12];
	  	    double zoneCWs_a[][] = new double[zoneWin.size()][12];
	  	    double zoneCWs_g_geff[][][] = new double[zoneWin.size()][2][12]; double zoneCWs_d_geff[][] = new double[zoneWin.size()][12];
	  	    double zoneCWs_g_Qs[][][] = new double[zoneWin.size()][2][12];  double zoneCWs_d_Qs[][] = new double[zoneWin.size()][12];			
	  	   						
			 try //존의 창별 일사정보 가져오기
		        {
		    	    String line = "";
				    final String delimiter = ",";
		            String filePath = "C:\\javalecture\\day0806\\ZoneCW_Solar.csv";
		            FileReader fileReader = new FileReader(filePath);
		            BufferedReader reader = new BufferedReader(fileReader);
	          	    
		           for(int i = 0; (line = reader.readLine()) != null; i++) { 
					   
		            	String[] token = line.split(delimiter);
		            	if(i==0) { //첫번째 행 건너뛰기  
		            	}
		            	else { //두번째 행부터 계산 	  
		            	        CW zonecw = (CW) zoneCW.get(i-1);
			            		for(int mth = 0; mth< 12;mth++) {
		            			zoneCWs_Is[i-1][mth] = Double.parseDouble(token[mth+1]);
			            		}
		            		} 	
		            	}
		        }
		        catch (IOException e)
		        {
		            e.printStackTrace();
		        }
			 try //존의 창별 음영정보 가져오기
		        {
		    	    String line = "";
				    final String delimiter = ",";
		            String filePath = "C:\\javalecture\\day0806\\ZoneCW_shadow.csv";
		            FileReader fileReader = new FileReader(filePath);
		            BufferedReader reader = new BufferedReader(fileReader);
	       	    
		           for(int i = 0; (line = reader.readLine()) != null; i++) { 
					   
		            	String[] token = line.split(delimiter);
		            	if(i==0) { //첫번째 행 건너뛰기  
		            	}
		            	else { //두번째 행부터 계산 	  
		            	        CW zonecw = (CW) zoneCW.get(i-1);
			            		for(int mth = 0; mth< 12;mth++) {
		            			zoneCWs_Fs[i-1][mth] = Double.parseDouble(token[mth+1]);
			            		}
		            		} 	
		            	}
		        }
		        catch (IOException e)
		        {
		            e.printStackTrace();
		        }
			 try //존의 창별 가동계수정보 가져오기
		        {
		    	    String line = "";
				    final String delimiter = ",";
		            String filePath = "C:\\javalecture\\day0806\\ZoneCW_a.csv";
		            FileReader fileReader = new FileReader(filePath);
		            BufferedReader reader = new BufferedReader(fileReader);
	    	    
		           for(int i = 0; (line = reader.readLine()) != null; i++) { 
					   
		            	String[] token = line.split(delimiter);
		            	if(i==0) { //첫번째 행 건너뛰기  
		            	}
		            	else { //두번째 행부터 계산 	  
		            	        CW zonecw = (CW) zoneCW.get(i-1);
			            		for(int mth = 0; mth< 12;mth++) {
		            			zoneCWs_a[i-1][mth] = Double.parseDouble(token[mth+1]);
			            		}
		            		} 	
		            	}
		        }
		        catch (IOException e)
		        {
		            e.printStackTrace();
		        }
			 for(int i = 0; i<zoneCW.size(); i++) {        	   
				 CW zonecw = (CW) zoneCW.get(i);
		            	for(int mth = 0; mth< 12;mth++) {
		            		GeffCalc geffcalc= new GeffCalc();
		            		QStrCalc qstrcalc= new QStrCalc();
		    					zoneCWs_g_geff[i][0][mth] = geffcalc.Calc(zonecw.g_g(),zoneCWs_Fs[i][mth]); 	//비이용일   
		    					zoneCWs_g_geff[i][1][mth] = geffcalc.Calc(zonecw.g_g(),zoneCWs_Fs[i][mth]); 	//이용일 차양없을 경우	
		    					zoneCWs_g_geff[i][1][mth] = geffcalc.Calc(zonecw.g_g(),zoneCWs_Fs[i][mth],zonecw.gtot_g(), zoneCWs_a[i][mth]);	 //이용일 차양있을 경우
		    					
		    					zoneCWs_d_geff[i][mth] = geffcalc.Calc(zonecw.g_d(),zoneCWs_Fs[i][mth]); 	 		
	 	    			       zoneCWs_g_Qs[i][0][mth] = qstrcalc.Calc(zonecw.Ff_g(), zonecw.Area_g(),zoneCWs_g_geff[i][0][mth], zoneCWs_Is[i][mth]);	 
	 	    			       zoneCWs_g_Qs[i][1][mth] = qstrcalc.Calc(zonecw.Ff_g(), zonecw.Area_g(),zoneCWs_g_geff[i][1][mth], zoneCWs_Is[i][mth]);	 
	 	    			       zoneCWs_d_Qs[i][mth] = qstrcalc.Calc(zonecw.Ff_d(), zonecw.Area_d(),zoneCWs_d_geff[i][mth], zoneCWs_Is[i][mth]);	 
	 	    			       
		            		QStr_CW[0][mth] += (zoneCWs_g_Qs[i][0][mth] + zoneCWs_d_Qs[i][mth]);  
		            		QStr_CW[1][mth] += (zoneCWs_g_Qs[i][1][mth] + zoneCWs_d_Qs[i][mth]);  
		            	}

	      for(int hc =0; hc<=1; hc++) {
		        for(int wewd =0; wewd<=1; wewd++) {
		         		for(int mth = 0; mth< 12;mth++) {	 
		        		 QStr_tot[hc][wewd][mth] = QStr_Win[wewd][mth] + QStr_CW[wewd][mth];  
		         		}
		        }	
	         } 
	      } 
		 
	}
	//******************************************************************************************************************************************************************************투명 Qs계산끝

	
	//******************************************************************************************************************************************************************************Qv계산 
	
	void ZoneQV() {	
	for(int hc =0; hc<=1; hc++) {
		for(int wewd =0; wewd<=1; wewd++) {	
			double θv_mech[][]= new double [2][12];  
			

			for(int mth = 0; mth<= 11;mth++) {
				θv_mech[0][mth] = θe[mth] + ηV_mech * (θi_h_set - θe[mth]);	
				θv_mech[1][mth] = θe[mth] + ηV_mech * (θi_c_set - θe[mth]);	
				
				QVCalc qvcalc= new QVCalc();
				if(θi[hc][wewd][mth]>=θe[mth]) {
				QV_inf_sink[hc][wewd][mth] = qvcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], Zone_HV_inf[wewd]);
				QV_z_sink[hc][wewd][mth] = qvcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], Zone_HV_z[wewd]);
				QV_win_sink[hc][wewd][mth]= qvcalc.Calc_sink(θe[mth], θi[hc][wewd][mth], Zone_HV_win[wewd]);
				} else if (θi[hc][wewd][mth]<θe[mth]) {
				QV_inf_source[hc][wewd][mth] = qvcalc.Calc_source(θe[mth], θi[hc][wewd][mth], Zone_HV_inf[wewd]);	
				QV_z_source[0][1][mth] = qvcalc.Calc_source(θe[mth], θi[hc][wewd][mth], Zone_HV_z[wewd]);	
				QV_win_source[hc][wewd][mth] = qvcalc.Calc_source(θe[mth], θi[hc][wewd][mth], Zone_HV_win[wewd]);		
				}
				
				if(θi[hc][wewd][mth] >= θv_mech[hc][mth]) {
					QV_mech_sink[hc][wewd][mth] = qvcalc.Calc_sink(θv_mech[hc][mth], θi[hc][wewd][mth], Zone_HV_mech[wewd]);
					} else{
					QV_mech_source[hc][wewd][mth] = qvcalc.Calc_source(θv_mech[hc][mth], θi[hc][wewd][mth], Zone_HV_mech[wewd]);		
					}
				QVsink_tot[hc][wewd][mth]  = QV_inf_sink[hc][wewd][mth] + QV_win_sink[hc][wewd][mth]+ QV_z_sink[hc][wewd][mth] +QV_mech_sink[hc][wewd][mth];
				QVsource_tot[hc][wewd][mth]  = QV_inf_source[hc][wewd][mth] + QV_win_source[hc][wewd][mth] + QV_z_source[hc][wewd][mth] +QV_mech_source[hc][wewd][mth];
				}
			
	   }
	  }	
	}

	
	//******************************************************************************************************************************************************************************Qv계산끝 	

	void ZoneQI() {	
		
		//비이용일
		QI_P[0] = 0;  
		QI_fac[0] = 0;
		//이용일
		QI_P[1] = qI_p *zoneArea;
		QI_fac[1] = qI_fac *zoneArea;
		
		for(int hc =0; hc<=1; hc++) {
			for(int wewd =0; wewd<=1; wewd++) {
				for(int mth =0; mth<=11; mth++) {
					QI_L[hc][wewd][mth] =0;
					QI_tot[hc][wewd][mth] =QI_P[wewd]+QI_fac[wewd]+QI_L[hc][wewd][mth];
				}
			}
		}
	}


		void Zoneη() {
			
			ηCalc ηcalc= new ηCalc();
			ΔQc_bCalc ΔQc_bcalc = new ΔQc_bCalc();	
			
			double awe = (1 - dwd_a / 365) * 7;
			
			
			//대차축열량 및 축열열손실 계산 
	 for(int mth =0;mth<=11; mth++) {
		 dwd_mth[mth] = dmth[mth]*dwd_a/365;
		 dwe_mth[mth] = dmth[mth] - dwd_mth[mth];
						Qsink[0][0][mth] =QTsink_tot[0][0][mth] + QVsink_tot[0][0][mth] +QSopsink_tot[0][0][mth];
						Qsource[0][0][mth] =QTsource_tot[0][0][mth] + QVsource_tot[0][0][mth] +QSopsource_tot[0][0][mth]+ QStr_tot[0][0][mth] + QI_tot[0][0][mth];
						γ[0][0][mth] = Qsource[0][0][mth] / Qsink[0][0][mth];
						a[0][0][mth] = 1 + τ[0]/16;
						η[0][0][mth] =ηcalc.ηh_Calc(γ[0][0][mth], a[0][0][mth]);
						ΔQc_b[0][0][mth] = ΔQc_bcalc.Calc( Cwirk_A*zoneArea, θi_h_set , θi[0][0][mth], awe, Δθi_NA, Qsink[0][0][mth], η[0][0][mth], Qsource[0][0][mth]);
						ΔQc_sink[0][1][mth] = ΔQc_b[0][0][mth] * dwe_mth[mth] /dwd_mth[mth];
	 }
	 

		
		for(int hc =0; hc<=1; hc++) {
			for(int wewd =0; wewd<=1; wewd++) {
				for(int mth =0; mth<=11; mth++) {
					Qsink[hc][wewd][mth] =QTsink_tot[hc][wewd][mth] + QVsink_tot[hc][wewd][mth] +QSopsink_tot[hc][wewd][mth];
					Qsource[hc][wewd][mth] =QTsource_tot[hc][wewd][mth] + QVsource_tot[hc][wewd][mth] +QSopsource_tot[hc][wewd][mth]+ QStr_tot[hc][wewd][mth] + QI_tot[hc][wewd][mth];
					
					if(Qsink[hc][wewd][mth]==0) {
						γ[hc][wewd][mth] = Qsource[hc][wewd][mth] / 1;
					}else{
						γ[hc][wewd][mth] = Qsource[hc][wewd][mth] / Qsink[hc][wewd][mth];
					}
					
					a[hc][wewd][mth] = 1 + τ[wewd]/16;
					η[0][wewd][mth] =ηcalc.ηh_Calc(γ[0][wewd][mth], a[0][wewd][mth]);		
					η[1][wewd][mth] =ηcalc.ηc_Calc(γ[1][wewd][mth], a[1][wewd][mth]);				
				}
			}
		}			
	}

		void ZoneQb() {
			QbCalc qbcalc = new QbCalc();
			 for(int mth =0;mth<=11; mth++) {
				 
				 Qhb_we_day[mth] = qbcalc.Qhb_Calc(Qsink[0][0][mth],  η[0][0][mth] , Qsource[0][0][mth]);
				 Qhb_wd_day[mth] = qbcalc.Qhb_Calc(Qsink[0][1][mth], η[0][1][mth] , Qsource[0][1][mth]);
				 Qcb_we_day[mth] = qbcalc.Qcb_Calc( η[1][0][mth] , Qsource[1][0][mth]);
				 Qcb_wd_day[mth] = qbcalc.Qcb_Calc( η[1][1][mth] , Qsource[1][1][mth]);
				 
				 Qhb_we_mth[mth] = (Qhb_we_day[mth] * dwe_mth[mth] - ΔQc_b[0][0][mth])/1000 ; //kWh 단위
				 Qhb_wd_mth[mth] = (Qhb_wd_day[mth] * dwd_mth[mth] + ΔQc_sink[0][1][mth] )/1000;
				 Qcb_we_mth[mth] = (Qcb_we_day[mth] * dwe_mth[mth])/1000;
				 Qcb_wd_mth[mth] = (Qcb_wd_day[mth] * dwd_mth[mth])/1000;
				 
				 if(Double.isNaN(Qhb_we_mth[mth])) {
					 Qhb_we_mth[mth]=0;
				 }else {
					 Qhb_we_mth[mth]=Qhb_we_mth[mth];
				 }
				 
				 if(Double.isNaN(Qhb_wd_mth[mth])) {
					 Qhb_wd_mth[mth]=0;
				 }else {
					 Qhb_wd_mth[mth]=Qhb_wd_mth[mth];
				 }
				 
				 if(Double.isNaN(Qcb_we_mth[mth])) {
					 Qcb_we_mth[mth]=0;
				 }else {
					 Qcb_we_mth[mth]=Qcb_we_mth[mth];
				 }
				 
				 if(Double.isNaN(Qcb_wd_mth[mth])) {
					 Qcb_wd_mth[mth]=0;
				 }else {
					 Qcb_wd_mth[mth]=Qcb_wd_mth[mth];
				 }
				 

				 Qhb_we_a += Qhb_we_mth[mth];
				 Qhb_wd_a += Qhb_wd_mth[mth];
				 Qcb_we_a += Qcb_we_mth[mth];
				 Qcb_wd_a += Qcb_wd_mth[mth];
				 
				 System.out.println(Qhb_we_mth[mth]);			
				 
			 }
			 
			// System.out.println(Qhb_we_a+",   "+Qhb_wd_a+",   "+Qcb_we_a+",   "+Qcb_wd_a);
	
	}		
	
}
