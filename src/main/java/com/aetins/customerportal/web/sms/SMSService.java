/*
 * package com.aetins.customerportal.web.sms;
 * 
 * 
 * import java.io.ByteArrayOutputStream; import java.io.OutputStream; import
 * java.net.URL;
 * 
 * import javax.net.ssl.HttpsURLConnection;
 * 
 * import org.apache.xmlbeans.impl.soap.MessageFactory; import
 * org.apache.xmlbeans.impl.soap.Node; import
 * org.apache.xmlbeans.impl.soap.SOAPBody; import
 * org.apache.xmlbeans.impl.soap.SOAPBodyElement; import
 * org.apache.xmlbeans.impl.soap.SOAPEnvelope; import
 * org.apache.xmlbeans.impl.soap.SOAPMessage; import
 * org.apache.xmlbeans.impl.soap.SOAPPart; import
 * org.springframework.stereotype.Controller; import
 * org.springframework.web.bind.annotation.GetMapping; import
 * org.springframework.web.bind.annotation.RequestMapping; import
 * org.w3c.dom.Document;
 * 
 * import requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.ARTNCIS;
 * import
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.ARTNCIS.IDOCDATA;
 * import
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.ARTNCIS.IDOCDATA.
 * HEADER; import
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.ARTNCIS.IDOCDATA.
 * PAYLOAD; import
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.ARTNCIS.IDOCDATA.
 * PAYLOAD.DATA; import
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.ARTNCIS.IDOCDATA.
 * PAYLOAD.DATA.Request; import requestdynamic.schemas.smsservice.ncis.art.
 * ArrayOfARTNCISIDOCDATAPAYLOADDATARequestParam; import
 * requestdynamic.schemas.smsservice.ncis.art.
 * ArrayOfARTNCISIDOCDATAPAYLOADDATARequestParam.Param;
 * 
 * @Controller
 * 
 * @RequestMapping(value = "/sms") public class SMSService {
 * 
 * @GetMapping(value = "/testing") public void service() {
 * 
 * try {
 * 
 * System.out.println("TEST SMS "); SOAPMessage sms = MessageFactory.newInstance
 * ().createMessage (); SOAPPart prt = sms.getSOAPPart (); SOAPEnvelope env =
 * prt.getEnvelope (); SOAPBody bdy = env.getBody ();
 * 
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument art =
 * requestdynamic.schemas.smsservice.ncis.art.ARTNCISDocument.Factory.
 * newInstance();
 * 
 * //ARTNCIS ARTNCIS sms1 = art.addNewARTNCIS();
 * 
 * //DOC data IDOCDATA docData = sms1.addNewIDOCDATA();
 * 
 * //Header HEADER header = docData.addNewHEADER();
 * header.setAuthKey("12345678"); header.setDocNum("0548746291");
 * header.setMsgID("D76B0BD6464F"); header.setTranID("D76B0BD6464F");
 * header.setTranDesc("SEND SMS"); header.setSourceAppID("Penta");
 * header.setDestAppID("ART");
 * 
 * //Pay load PAYLOAD payLoad = docData.getPAYLOAD();
 * 
 * //Data DATA data = payLoad.getDATA();
 * 
 * //Request Request req = data.getRequest(); req.setNationality("INDIAN");
 * req.setSMSCode("1038"); req.setRecipientNumber("0548746291");
 * 
 * //Request Param ArrayOfARTNCISIDOCDATAPAYLOADDATARequestParam requestParam =
 * req.getParamList();
 * 
 * Param[] paramArray = requestParam.getParamArray();
 * 
 * Param param = paramArray[0]; param.setName("PolicyNo");
 * param.setValue("PO-MTS-987876");
 * 
 * requestParam.setParamArray(paramArray);
 * 
 * req.setParamList(requestParam);
 * 
 * data.setRequest(req);
 * 
 * payLoad.setDATA(data);
 * 
 * docData.setHEADER(header); docData.setPAYLOAD(payLoad);
 * 
 * 
 * sms1.setIDOCDATA(docData);
 * 
 * Node nd = (Node) sms1.getDomNode(); SOAPBodyElement ele =
 * bdy.addDocument((Document) nd);
 * 
 * // URL endpoint = new
 * URL("http://10.0.5.130/ART.NCIS.SMSService/Service.asmx"); HttpsURLConnection
 * con = (HttpsURLConnection) endpoint.openConnection();
 * System.out.println("#####################################");
 * System.out.println("#####################################");
 * System.out.println("##########CONNECTION###########");
 * System.out.println("#####################################");
 * System.out.println("#####################################");
 * System.out.println("#####################################");
 * 
 * //SOAPMessage response = con.call(ele, endpoint);
 * //response.writeTo(System.out); con.setDoOutput(true);
 * con.setInstanceFollowRedirects( false ); con.setRequestMethod( "POST" );
 * con.setRequestProperty( "Content-Type", "application/x-www-form-urlencoded");
 * 
 * try(OutputStream os = con.getOutputStream()) {
 * 
 * ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
 * sms.writeTo(byteArrayOut); byte[] byteArray = byteArrayOut.toByteArray();
 * os.write(byteArray, 0, byteArray.length); }
 * 
 * } catch (Exception e) { e.printStackTrace(); } } }
 */