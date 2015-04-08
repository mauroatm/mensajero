/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mensajero;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 *
 * @author mauricm
 */
public class Controller {

    private final String USER_AGENT = "Mozilla/5.0";

    // HTTP POST request
    void sendPost(String introUrl, int protocolo,String Mensaje ) throws Exception {

        String url = introUrl;
        URL obj = new URL(url);
        HttpURLConnection con = null;
        if(protocolo == 0){
         con = (HttpURLConnection) obj.openConnection();
            System.out.println("Selecion protocollo http");
        }else{
            con = (HttpsURLConnection) obj.openConnection();
        }
               

        //add reuqest header
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
        urlParameters = Mensaje;

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(urlParameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + urlParameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine = "Envio algo";  //"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NTKdcHF3c/8AXU0yNSx45JqZ/Exx+FC1IkWRuc7V/nT0QKcBfMk9B0FdBpXhbVNSKypbMV/56yHYg/E0km9EDkkrsxUh39B5aevc1dtrIGUJBE0rngBRkn8K7e28FadbASapfGdx/wAsoPlX/vo9fwFTXfizQfDcOy1Fvb4H/LH75+rdTXTHCzteWi8zmli4XtBcz8jJsfA+pXKrJdtHYwnvN978FHNb9toXh7Rl82dWvJF6tcHan/fI/qa891j4rSzFksITz/G/+c1w+oeItU1Nibi7kKn+FTgUOeGp7e8/wBUsTV3fKvxPaNY+JumafEYIplIXpFAoVfyFee6v8TdSvNyWaiBOxPXFcKTk5PWisZ42pJWjovI2p4GlB3er8y1eajeX7l7q4klJOfmNVaKK5G23dnWklogooopDCiiigAooooAKKKKACiiigAooooAKKKKAFV2RtyMVPqDitCHWbhABKFmX/a6/nWdRVKTjsJxT3OittVtmPySvbt6P0NXiyOoMkSuP78Rrj6lhuJrc5ikZD7GtY131MpUV0Olks45/9VIGPo3DCtPwjbSW+ukSKR+7bFcvFrLHi4iV/wDaXg1taVrYguo5La5AIb/Vy8Z9q2pThzqRlUjPkcS3Dpt3qOo3EdtBJKTKRhFJrrLHwBdFc6hcpZRY5RPnkP1xwKZL4/0vQrD7JZsHfqwiHBY8nJrjNW+Imq37MsGIIzx1ycVtKOHpu85XfZHPF4iorQVl3Z6lGPDPhiMtHHC0gH+tuSHYfQdB+Vc3rXxYhBKWoa4YcBs/KK8nuLq4unL3EzyMe7NmoayljWtKUbGscCm71XzHQ6p401jVGO64aJD/AAof61gO7yNudmZvVjk02iuSdSU3eTudkIRgrRVgoooqCgooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigAooooAKKKKACiiigD//2Q==";

        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        //print result
        System.out.println(response.toString());

    }

    public void setServe(String intoUrl) throws Exception {
        URL url = new URL(intoUrl);

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"))) {
            for (String line; (line = reader.readLine()) != null;) {
                System.out.println(line);
            }
        }
    }

    void sendPost(String miUrl) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
