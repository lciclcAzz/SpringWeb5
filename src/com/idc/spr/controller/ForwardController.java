package com.idc.spr.controller;
//
//
//import java.sql.Connection;
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Map;
//import java.util.ResourceBundle;
//import java.util.Set;
//
//import javax.servlet.http.*;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
////import com.pccth.bean.EventLogBean;
//import com.pccth.rtn.erpss.dto.SsoDataDTO;
////import com.pccth.log.LogUtil;
//import com.pccth.utils.CryptURL;
//import com.pccth.utils.DBUtil;
//import com.pccth.utils.DataUtil;
//import com.pccth.utils.Day;
//import com.pccth.utils.Tools;
//
//import org.slf4j.LoggerFactory;
//
//@Controller
//@RequestMapping(value="forward")
public class ForwardController {
//	private static Logger log = LoggerFactory.getLogger(ForwardController.class);
////	private static String environment = ResourceBundle.getBundle("app").getString("Environment");
////	private final static String WHTMOS_LINK = ResourceBundle.getBundle("app").getString(environment+"_WHTMOS_LINK");
////	private final static String FDE_MOS = ResourceBundle.getBundle("app").getString("FDE_MOS");
////	private final static String LOG_TYPE = ResourceBundle.getBundle("app").getString("LOG_TYPE");
//	
//	@RequestMapping(method=RequestMethod.GET)
//	public String showForm (HttpSession session, HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		//log.debug("=============In FDE forward GET============>"+Function.getHostName());
//		String forward ="";
//		SsoDataDTO ssoFde =(SsoDataDTO) session.getAttribute("SsoDataDTO");
//		System.out.println("In FDE forward GET ssoFde=="+ssoFde);
//		String page =Tools.chkNull(request.getParameter("page"));	
//		//=================================================
//		Set keys = request.getParameterMap().keySet();
//		String paramValue =getMapParam(request, keys);
//       //===================================================================
//		/*String pageCheck ="";
//		if (!page.equals("")){
//		int c =page.indexOf("/");
//		pageCheck =page.substring((c+1));
//		}*/
//		//String[] fdeMos =FDE_MOS.split("\\|");
//		String link="";
//		//log.debug("WHTMOS_LINK=="+WHTMOS_LINK);
//		//log.debug("fdeMos.length=="+fdeMos.length);
//		try{
//		/*if(fdeMos.length>0){
//			for(int i=0;i<fdeMos.length;i++){
//				log.debug("fdeMos[i]=="+fdeMos[i]+"  pageCheck=="+pageCheck);
//				if (fdeMos[i].equalsIgnoreCase(pageCheck)){
//					link="Y";
//				}
//			}
//		}*/
//		log.debug("link=="+link);
//		if (link.equals("")){
//			if (page.equals("")) forward ="redirect:exitProgram?returnCode=I0025";
//			else forward= "redirect:"+page+paramValue;
//		}else{
//			/*String param="";
//			SsoDataDTO sso =(SsoDataDTO) session.getAttribute("SsoDataDTO");
//
//			String applicationName= Tools.chkNull(sso.getApplicationName());
//			String clientLocation= Tools.chkNull(sso.getClientLocation());
//			String firstTime= Tools.chkNull(sso.getFirstTime());
//			String locationMachineNumber= Tools.chkNull(sso.getLocationMachineNumber());
//			String ssoType= Tools.chkNull(sso.getSsoType());
//			String systemId= Tools.chkNull(sso.getSystemId());
//			String systemLocationGroup= Tools.chkNull(sso.getSystemLocationGroup());
//			String systemName= Tools.chkNull(sso.getSystemName());
//			String systemPrivileges= Tools.chkNull(sso.getSystemPrivileges());
//			String systemTransactions= Tools.chkNull(sso.getSystemTransactions());
//			String systemUserGroup= Tools.chkNull(sso.getSystemUserGroup());
//			String tokenId= Tools.chkNull(sso.getTokenId());
//			String userFullName= Tools.chkNull(sso.getUserFullName());
//			String userGrpName= Tools.chkNull(sso.getUserGrpName());
//			String userId= Tools.chkNull(sso.getUserId());
//			String userOfficeCode= Tools.chkNull(sso.getUserOfficeCode());
//			String userOfficeName= Tools.chkNull(sso.getUserOfficeName());
//			String userRDOfficeCode= Tools.chkNull(sso.getUserRDOfficeCode());
//			String fdeUrl= Tools.chkNull(sso.getFdeUrl());
//			String mosParam= Tools.chkNull(sso.getMosParam());
//			log.debug("fdeUrl1=="+fdeUrl);
//			if (!applicationName.equals("")) applicationName =CryptURL.ENC(applicationName);
//			if (!clientLocation.equals("")) clientLocation =CryptURL.ENC( clientLocation);
//			if (!firstTime.equals("")) firstTime =CryptURL.ENC( firstTime);
//			if (!locationMachineNumber.equals("")) locationMachineNumber =CryptURL.ENC( locationMachineNumber);
//			if (!ssoType.equals("")) ssoType =CryptURL.ENC( ssoType);
//			if (!systemId.equals("")) systemId =CryptURL.ENC( systemId);
//			if (!systemLocationGroup.equals("")) systemLocationGroup =CryptURL.ENC( systemLocationGroup);
//			if (!systemName.equals("")) systemName =CryptURL.ENC( systemName);
//			if (!systemPrivileges.equals("")) systemPrivileges =CryptURL.ENC( systemPrivileges);
//			if (!systemTransactions.equals("")) systemTransactions =CryptURL.ENC( systemTransactions);
//			if (!systemUserGroup.equals("")) systemUserGroup =CryptURL.ENC( systemUserGroup);
//			if (!tokenId.equals("")) tokenId =CryptURL.ENC( tokenId);
//			if (!userFullName.equals("")) userFullName =CryptURL.ENC( userFullName);
//			if (!userGrpName.equals("")) userGrpName =CryptURL.ENC( userGrpName);
//			if (!userId.equals("")) userId =CryptURL.ENC( userId);
//			if (!userOfficeCode.equals("")) userOfficeCode =CryptURL.ENC( userOfficeCode);
//			if (!userOfficeName.equals("")) userOfficeName =CryptURL.ENC( userOfficeName);
//			if (!userRDOfficeCode.equals("")) userRDOfficeCode =CryptURL.ENC( userRDOfficeCode);
//			if (!fdeUrl.equals("")) fdeUrl =CryptURL.ENC( fdeUrl);
//			param+="&applicationname="+applicationName;
//			param+="&clientLocation="+clientLocation;
//			param+="&firstTime="+firstTime;
//			param+="&locationMachineNumber="+locationMachineNumber;
//			param+="&ssoType="+ssoType;
//			param+="&systemId="+systemId;
//			param+="&systemLocationGroup="+systemLocationGroup;
//			param+="&systemName="+systemName;
//			param+="&systemPrivileges="+systemPrivileges;
//			param+="&systemTransactions="+systemTransactions;
//			param+="&systemUserGroup="+systemUserGroup;
//			param+="&tokenId="+tokenId;
//			param+="&userFullName="+userFullName;
//			param+="&userGrpName="+userGrpName;
//			param+="&userId="+userId;
//			param+="&userOfficeCode="+userOfficeCode;
//			param+="&userOfficeName="+userOfficeName;
//			param+="&userRDOfficeCode="+userRDOfficeCode;
//			param+="&fdeUrl="+fdeUrl;
//			param+="&formFde=Y";
//			log.debug("fdeUrl2=="+fdeUrl);
//			log.debug("mosParam=="+mosParam);
//			if (mosParam.equals("")){
//			forward= "redirect:"+WHTMOS_LINK+"forward?page="+page+param;//+Tools.chkNull(sso.getMosParam());
//			}else{
//			forward= "redirect:"+WHTMOS_LINK+"forward?page="+page+param+mosParam;
//			}*/
//		}
//		
//		/*writeEvenLog(pageCheck.toUpperCase(),ssoFde,"Y");
//		String returnCode =Function.callSsoAppTransactionService(ssoFde.getUserId(), ssoFde.getSystemId(), ssoFde.getTokenId());
//		log.info("FDE forward1 =="+forward);
//		//returnCode="";
//		if (!returnCode.equalsIgnoreCase("P")||returnCode.equalsIgnoreCase("")) forward ="redirect:exitProgram?returnCode=E0066";
//		//ssoFde.setReturnCode(returnCode);
//		//session.setAttribute("SsoDataDTO",ssoFde);*/
//		log.info("forward =="+forward);
//		}catch (Exception e) {
//			//writeEvenLog(pageCheck.toUpperCase(),ssoFde,"N");
//		}
//		
//		//if (session == null) log.debug("In FDE forward null session1xx==");
//		//log.debug("In FDE forward GET session1xx=="+session);
//		
//		return forward;
//	}
//	@RequestMapping(value="/fdeurl",method=RequestMethod.GET)
//	public String showForm2 (HttpSession session,HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//		log.debug("=============In FDE forward GET2============");
//		String mosProgramId =Tools.chkNull(request.getParameter("mosProgramId"));
//		String pageCheck ="";
//		if (!mosProgramId.equals("")){
//		int c =mosProgramId.indexOf("/");
//		pageCheck =mosProgramId.substring((c+1));
//		}
//		String mosProgramName =getProgramName(pageCheck);//CryptURL.DEC(Tools.chkNull(request.getParameter("mosProgramName")));
//		log.debug("mosProgramId=="+mosProgramId);
//		log.debug("mosProgramName=="+mosProgramName);
//		//HttpSession session1 =request.getSession(true);
//		//session1.removeAttribute("SsoDataDTO");
//		//session1.invalidate(); 
//		//=================================================
//		Set keys = request.getParameterMap().keySet();
//		String paramValue =getMapParam2(request, keys);
//       //===================================================================
//		//log.debug("paramValue=="+paramValue);
//		SsoDataDTO ssoFde =(SsoDataDTO) session.getAttribute("SsoDataDTO");
//		//log.debug("ssoFde=="+ssoFde);
//		String forward ="";
//		if (ssoFde != null){
//			log.info("Set SsoData from Mos");
//			
//		ssoFde.setMosProgramCode(mosProgramId);
//		ssoFde.setMosProgramName(mosProgramName);
//		ssoFde.setMosParam(paramValue);
//		session.setAttribute("SsoDataDTO",ssoFde);
//		SsoDataDTO sso =(SsoDataDTO) session.getAttribute("SsoDataDTO");
//		log.debug(" sso=="+sso);
//		//log.debug("getMosProgramCode sso=="+sso.getMosProgramCode());
//		//log.debug("mosProgramName sso=="+sso.getMosProgramName());
//		//log.debug("re=="+"redirect:"+sso.getFdeUrl()+"WHTFDE/view/tmpPage");
//		forward= "redirect:"+sso.getFdeUrl()+"WHTFDE/view/tmpPage";//"redirect:"+WHTMOS_LINK+"forward?page="+mosProgramId;
//		}
//		return forward;
//	}
//	/*
//	 * �� write log ŧ DB ���� txt file
//	 * param programId
//	 */
////	public void writeEvenLog(String programId,SsoDataDTO sso,String eventStatus) throws SQLException{
////		log.debug("writeEvenLog programId=="+programId);
////		Connection con =null;
////		int c=0;
////		try{
////		con = DBUtil.getConnection(false);	
////		String db="",txt="";
////		String[] logType =LOG_TYPE.split(",");
////		if(logType.length>0){
////			for(int i=0;i<logType.length;i++){
////				log.debug("logType[i]=="+logType[i]);
////				if (logType[i].equals("DB")) db="Y";
////				if (logType[i].equals("TXT")) txt="Y";
////			}
////		}
////		if (db.equals("Y")){
////			c=0;
////			c=insertToEvenDB(programId,sso,eventStatus);
////		}
////		if (txt.equals("Y")){
////			//c=0;
////			insertTologTxt(programId,sso,eventStatus);
////		}
////		if (c>0) con.commit(); else con.rollback();
////		}catch (Exception e) {
////			con.rollback();
////		}finally{
////			try{
////	            if (con != null){
////	                con.close();
////	            }
////	        }catch(Exception e){
////	            System.out.println(": Close Connection Exception Error : " + e);
////	        }
////			con=null;
////		}
////	}
//	
//	/*
//	 * �� write log ŧ DB 
//	 * param programId
//	 */
//	/*public static int insertToEvenDB(String programId,SsoDataDTO sso,String eventStatus){
//		int c=0;
//		Day today =new Day();
//		today.setYearType('E');
//		EventLogBean eventLog =new EventLogBean();
//		eventLog.setBrowserName(Tools.chkNull(sso.getBrowserName()));
//		eventLog.setEventActionName("Entry Program:"+programId);
//		eventLog.setEventDate(today.toString());
//		eventLog.setEventDescription("Entry");
//		eventLog.setEventStatus(eventStatus);
//		eventLog.setIpAddress(Tools.chkNull(sso.getIpAddress()));
//		eventLog.setProgramId(programId);
//		eventLog.setRemark("");
//		eventLog.setUserId(Tools.chkNull(sso.getUserId()));
//		try{
//			c=eventLog.insert();
//		}catch(Exception e){
//	        System.out.println(" Method insertToEvenDB : Rollback Exception Error : " + e);
//	        c = -1;	
//	    }
//		return c;
//	}*/
//	
//	/*
//	 * �� write  txt file
//	 * param programId
//	 */
//	/*public static  void insertTologTxt(String programId,SsoDataDTO sso,String eventStatus){
//		Day today =new Day();
//		HashMap<String, String> data = new HashMap<String, String>();
//		data.put("EVENT_DATE", today.toString());
//		data.put("PROGRAM_ID", programId);
//		data.put("USER_ID", Tools.chkNull(sso.getUserId()));
//		data.put("IP_ADDRESS", Tools.chkNull(sso.getIpAddress()));
//		data.put("EVEN_ACTION_NAME", "Entry Program:"+programId);
//		data.put("EVENT_DESCRIPTION", "Entry");
//		data.put("EVENT_STATUS", eventStatus);
//		data.put("BROWSER_NAME", Tools.chkNull(sso.getBrowserName()));
//		data.put("REMARK", "");
//		LogUtil log =new LogUtil();
//		log.writeLog(data);
//	}*/
//	
//	public String getMapParam( HttpServletRequest request,Set keys ){
//		String paramValue="";
//		int c=0;
//		for (Iterator set = keys.iterator(); set.hasNext();) {
//            String key = (String) set.next();
//            String value = new String(request.getParameter(key));
//            if (c==0) paramValue +="?"+key+"="+value; else paramValue +="&"+key+"="+value;
//            c++;
//		}
//		return paramValue;
//	}
//	public String getMapParam2( HttpServletRequest request,Set keys ){
//		String paramValue="";
//		int c=0;
//		for (Iterator set = keys.iterator(); set.hasNext();) {
//            String key = (String) set.next();
//            String value = new String(request.getParameter(key));
//            paramValue +="&"+key+"="+value;
//            c++;
//		}
//		return paramValue;
//	}
//	public String getProgramName(String programCode){
//		String programName = "";
//		Connection con = null;
//		Day today = new Day();
//		List<Map> lResult = null;
//		String sql = "";
//		try {
//			con = DBUtil.getConnection(false);
//			sql = "Select  PROGRAM_NAME from .PROGRAM where PROGRAM_ID ='"+programCode.toUpperCase()+"'";
//				log.debug("sql=="+sql);
//			lResult = DataUtil.executeQuery(con, sql, Map.class);
//			if (lResult.size() > 0) {
//				for (int i = 0; i < lResult.size(); i++) {
//					Map<String, String> map = lResult.get(i);
//					programName=map.get("PROGRAM_NAME");
//				}
//			}
//			con.commit();
//		}catch(Exception e){
//            log.debug("getProgramName : Rollback Exception Error : " + e);
//             try{
//                if (con != null){
//                    con.rollback();
//                }
//            }catch(Exception ee){
//            	log.debug("getProgramName : Rollback Exception Error : " + ee);
//            }
//            //throw new Exception("FDEE001Service Method getBC12ByKey : Exception Error : " + e);
//        }finally{
//            try{
//                if (con != null){
//                    con.close();
//                }
//            }catch(Exception e){
//            	log.debug("getProgramName : Rollback Exception Error : " + e);
//                //throw new Exception("FDEE001Service Method getBC12ByKey : Exception Error : " + e);
//            }
//        }	
//		return programName;
//	}
}