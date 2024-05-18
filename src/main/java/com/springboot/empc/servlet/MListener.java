// package com.springboot.empc.servlet;

// import java.io.IOException;
// import java.io.PrintWriter;

// import javax.servlet.ServletConfig;
// import javax.servlet.ServletException;
// import javax.servlet.annotation.WebServlet;
// import javax.servlet.http.HttpServlet;
// import javax.servlet.http.HttpServletRequest;
// import javax.servlet.http.HttpServletResponse;
// import javax.servlet.http.HttpSession;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

// /**
// * &mode=download&format=pdf&reportname=test
// */
// @WebServlet("/MListener")
// public class MListener extends HttpServlet {
// private static final Logger log = LoggerFactory.getLogger(MListener.class);
// private static final long serialVersionUID = 1L;
// private MongoDbService mongoDbServiceConfig;
// private MongoDbService mongoDbServiceData;
// private Router mRouter;

// public MListener() {
// super();
// }

// public void init(ServletConfig config) throws ServletException {
// mongoDbServiceConfig = new MongoDbService(Constants.APP_NAME,
// MQBConstants.CONFIG_DB, true);
// mongoDbServiceData = new MongoDbService(Constants.APP_NAME,
// MQBConstants.DATA_DB, true);
// mRouter = new Router();
// }

// protected void doGet(HttpServletRequest request, HttpServletResponse
// response) throws ServletException, IOException {
// String returnData = "";
// response.addHeader("Access-Control-Allow-Origin", "*");
// HttpSession session = request.getSession(false);
// boolean existIP=ListenerUtility.existIP(request.getRemoteAddr());
// String ip=request.getRemoteAddr();
// log.debug("IP "+ip+" existIP Flag ::"+existIP);
// if(existIP){
// log.debug("IP be whitelisted No need to check session
// "+request.getRemoteAddr());
// }
// else if(Config.getConfig().getCoreConfig().isSessioncheck()){
// if(session == null){
// log.debug("No valid session found");
// response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
// return;
// }
// else{
// //com.pinnacle.specta.rs.helpers.DataAPIUtility.updateUserHistory(mMongoDataDatabase,
// request,0);
// log.debug("Valid session found with id : " + session.getId());
// }
// }
// String
// username=session==null?(request.getParameter("user")==null?ip:request.getParameter("user")):(String)session.getAttribute("username");
// if(request.getParameter("action").equals("ping")){
// log.trace("***** Ping Request ******");
// returnData = "SPECTA REPORT SERVER (" + Constants.Version +") IS ALIVE";
// }else{
// returnData =
// mRouter.process(request,username,mongoDbServiceConfig,mongoDbServiceData);
// }
// PrintWriter out = response.getWriter();
// out.print(returnData);
// out.flush();
// }

// /**
// * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
// response)
// */
// protected void doPost(HttpServletRequest request, HttpServletResponse
// response) throws ServletException, IOException {
// doGet(request,response);
// }

// }