package com.manipal.mail.route

import com.manipal.mail.mail.SendMail
import org.json.JSONObject

class Route {
    private var sendMail = SendMail()
    private var emailPattern = Regex("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    fun quoteMailer(data: String) {

        val jsonData = JSONObject(data)

        var email = ""
        if (jsonData.getJSONObject("formData").has("email")) {
            email = jsonData.getJSONObject("formData").getString("email")
        }
        var userName = "Customer"
        if (jsonData.getJSONObject("formData").has("fullName")) {
            userName=jsonData.getJSONObject("formData").getString("fullName")
        }
        val quotes = jsonData.getJSONArray("quotes")

        var mjmlTags ="""
            <mjml>
              <mj-body background-color="#fff" font-size="13px">
                <mj-section background-color="#fae716" padding-bottom="20px" padding-top="20px">
                  <mj-column width="100%">
                    <mj-image src="https://i.ibb.co/xzSWHHT/favicon.png" alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Insurance Bazaar</span>
                      <br />
                      
                    </mj-text>
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                </mj-section>
                <mj-section background-color="#fae716" padding-bottom="0px" padding-top="0">
                  <mj-column width="100%">
                    <mj-text align="center" font-size="13px"  font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="18px" padding-top="28px">HELLO
                      <p style="font-size:16px;">$userName</p>
                    </mj-text>
                  </mj-column>
                </mj-section>
                <mj-section background-color="#ffff" padding-bottom="5px" padding-top="0">
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">checkout the quotes below</span>
                      <br />
                      
                    </mj-text>
                  </mj-column>
                </mj-section>
                """
        for (i in 0 until quotes.length()) {
            val keys = quotes.getJSONObject(i).getJSONObject("quote").keys()

            val quote = quotes.getJSONObject(i)
            mjmlTags += """<mj-section background-color="#ffff" padding-bottom="15px">
                  <mj-column>
                  <mj-image src="${quote.getString("image")}" alt="" align="center" border="none" width="50px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                  </mj-column>
                  <mj-column>
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Partner:${quote.getString("partner")}</strong></mj-text>
                    
                  </mj-column>"""
            for (key in keys) {
                mjmlTags += """<mj-column>
                      <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>${key}:${quote.getJSONObject("quote").getString(key as String?)}</strong></mj-text>
                
                      </mj-column>"""
            }

            mjmlTags += "</mj-section>"

        }
        mjmlTags += """<mj-section background-color="#fae716" padding-bottom="5px" padding-top="0">
                      <mj-column width="100%">
                        <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                        <mj-text align="center"  font-size="15px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="20px" padding-top="20px">Best,
                          <br />
                          <span style="font-size:15px">The Insurance Bazaar Team</span>
                        </mj-text>
                      </mj-column>
                    </mj-section>
                  </mj-body>
                </mjml>"""
        if (emailPattern.matches(email)) {
            sendMail.sendMail(arrayListOf(email), "the quotes for you only", mjmlTags)
        }

    }

    fun newInsuranceMailer(email: ArrayList<String>, data: String) {
        val jsonData = JSONObject(data)
        val mjmlTag = """
            <mjml>
              <mj-body background-color="#fff" font-size="13px">
                <mj-section background-color="#fae716" padding-bottom="20px" padding-top="20px">
                  <mj-column width="100%">
                    <mj-image src="https://i.ibb.co/xzSWHHT/favicon.png" alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Insurance Bazaar</span>
                      <br />
                      
                    </mj-text>
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                </mj-section>
                <mj-section background-color="#fae716" padding-bottom="0px" padding-top="0">
                  <mj-column width="100%">
                    <mj-text align="center" font-size="13px"  font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="18px" padding-top="28px">HELLO
                      <p style="font-size:16px;">Customer</p>
                    </mj-text>
                  </mj-column>
                </mj-section>
                <mj-section background-color="#ffff" padding-bottom="5px" padding-top="0">
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Check Out our New Insurance</span>
                      <br />
                      
                    </mj-text>
                  </mj-column>
                </mj-section>
                <mj-section background-color="#ffff" padding-bottom="15px">
                  
                
                  <mj-column width="100%">
                    <mj-image src=            ${jsonData.getString("image")}             alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Category:            ${jsonData.getString("category")}            </strong></mj-text>
                    
                  
                  
                  
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Product:            ${jsonData.getString("product")}            </strong></mj-text>
                    
                  
                  
                    <mj-text align="center" font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>            ${jsonData.getString("info")}            </strong></mj-text>
                    <mj-text align="center" font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>For more info check out our website.</strong></mj-text>
                </mj-section>
                
                <mj-section background-color="#fae716" padding-bottom="5px" padding-top="0">
                  
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="15px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="20px" padding-top="20px">Best,
                      <br />
                      <span style="font-size:15px">The Insurance Bazaar Team</span>
                    </mj-text>
                  </mj-column>
                </mj-section>
              </mj-body>
            </mjml>
        """.trimIndent()
        sendMail.sendMail(email, "New Insurance!!!", mjmlTag)
    }

    fun suggestionMailer(data: String) {
        println("suggestionMailer")
        println(data)
        val jsonData = JSONObject(data)
        var userName="Customer"
        if(jsonData.has("userName")&&jsonData.getString("userName")!="null"){

            userName=jsonData.getString("userName")
        }
        var mjmlTag = """
            <mjml>
              <mj-body background-color="#fff" font-size="13px">
                <mj-section background-color="#fae716" padding-bottom="20px" padding-top="20px">
                  <mj-column width="100%">
                    <mj-image src="https://i.ibb.co/xzSWHHT/favicon.png" alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Insurance Bazaar</span>
                      <br />
                      
                    </mj-text>
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                </mj-section>
                <mj-section background-color="#fae716" padding-bottom="0px" padding-top="0">
                  <mj-column width="100%">
                    <mj-text align="center" font-size="13px"  font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="18px" padding-top="28px">HELLO
                      <p style="font-size:16px;">$userName</p>
                    </mj-text>
                  </mj-column>
                </mj-section>
                <mj-section background-color="#ffff" padding-bottom="5px" padding-top="0">
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Check out other insurance</span>
                      <br />
                      
                    </mj-text>
                  </mj-column>
                </mj-section>
                """
        for (i in 0 until jsonData.getJSONArray("category").length()) {
            val jsonData1 = jsonData.getJSONArray("category").getJSONObject(i)
            mjmlTag += """<mj-section background-color="#ffff" padding-bottom="15px">
                  
                
                  <mj-column width="100%">
                    <mj-image src=            ${jsonData1.getString("image")}             alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Category:            ${jsonData1.getString("category")}            </strong></mj-text>
                    
                  
                  
                  
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Product:            ${jsonData1.getString("product")}            </strong></mj-text>
                    
                  
                  
                    <mj-text align="center" font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>            ${jsonData1.getString("info")}            </strong></mj-text>
                    
                </mj-section>"""
        }
        mjmlTag += """<mj-section background-color="#fae716" padding-bottom="5px" padding-top="0">
                  
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="15px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="20px" padding-top="20px">Best,
                      <br />
                      <span style="font-size:15px">The Insurance Bazaar Team</span>
                    </mj-text>
                  </mj-column>
                </mj-section>
              </mj-body>
            </mjml>
        """.trimIndent()
        if (jsonData.has("email") && emailPattern.matches(jsonData.getString("email"))) {
            sendMail.sendMail(arrayListOf(jsonData.getString("email")), "The Insurance you can check", mjmlTag)
        }
    }

    fun newPartnerMailer(email: ArrayList<String>, data: String) {
        val jsonData = JSONObject(data)
        val mjmlTag = """
            <mjml>
              <mj-body background-color="#fff" font-size="13px">
                <mj-section background-color="#fae716" padding-bottom="20px" padding-top="20px">
                  <mj-column width="100%">
                    <mj-image src="https://i.ibb.co/xzSWHHT/favicon.png" alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Insurance Bazaar</span>
                      <br />
                      
                    </mj-text>
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                </mj-section>
                <mj-section background-color="#fae716" padding-bottom="0px" padding-top="0">
                  <mj-column width="100%">
                    <mj-text align="center" font-size="13px"  font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="18px" padding-top="28px">HELLO
                      <p style="font-size:16px;">Customer</p>
                    </mj-text>
                  </mj-column>
                </mj-section>
                <mj-section background-color="#ffff" padding-bottom="5px" padding-top="0">
                  <mj-divider  border-width="2px" border-style="solid" padding-left="20px" padding-right="20px" padding-bottom="0px" padding-top="0"></mj-divider>
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="13px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="28px" padding-top="28px"><span style="font-size:20px; font-weight:bold">Check Out New Partner</span>
                      <br />
                      
                    </mj-text>
                  </mj-column>
                </mj-section>
                <mj-section background-color="#ffff" padding-bottom="15px">
                  
                
                  <mj-column width="100%">
                    <mj-image src=            ${jsonData.getString("image")}             alt="" align="center" border="none" width="100px" padding-left="0px" padding-right="0px" padding-bottom="10px" padding-top="10px"></mj-image>
                   
                  </mj-column>
                  <mj-text align="center" font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Partner:            ${jsonData.getString("partner")}            </strong></mj-text>
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Category:            ${jsonData.getString("category")}            </strong></mj-text>
                    
                  
                  
                  
                    <mj-text align="center"  font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>Product:            ${jsonData.getString("product")}            </strong></mj-text>
                    
                  
                  
              
                    <mj-text align="center" font-size="15px" font-family="Ubuntu, Helvetica, Arial, sans-serif" padding-left="25px" padding-right="25px" padding-bottom="0px"><strong>For more info check out our website.</strong></mj-text>
                </mj-section>
                
                <mj-section background-color="#fae716" padding-bottom="5px" padding-top="0">
                  
                  <mj-column width="100%">
                    
                    <mj-text align="center"  font-size="15px" font-family="Helvetica" padding-left="25px" padding-right="25px" padding-bottom="20px" padding-top="20px">Best,
                      <br />
                      <span style="font-size:15px">The Insurance Bazaar Team</span>
                    </mj-text>
                  </mj-column>
                </mj-section>
              </mj-body>
            </mjml>
        """.trimIndent()
        sendMail.sendMail(email, "New Partner!!!", mjmlTag)
    }
}