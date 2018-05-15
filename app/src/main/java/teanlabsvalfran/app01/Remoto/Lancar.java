package teanlabsvalfran.app01.Remoto;

import java.util.concurrent.ExecutionException;

/**
 * Created by Valfran on 13/04/2018.
 */

public class Lancar {

    public String getTodos(){
        String result = "";
        result = postOrcamento();
        result += " " + postOrcamentoServico();
        return result;

    }


    public String postOrcamento(){
        Enviar ev = new Enviar();
        String r = "Error";
        try {
            r = ev.postOrcamento();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r;
    }

    public String postOrcamentoServico(){
        Enviar ev = new Enviar();
        String r = "Error";
        try {
            r = ev.postOrcamentoServico();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return r;
    }


}
