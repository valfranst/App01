package teanlabsvalfran.app01.Remoto;

import org.json.JSONException;

import java.util.concurrent.ExecutionException;

/**
 * Created by Valfran on 26/02/2018.
 */

public class Chamar {

    public void getTodos(){
        int cont = 0;
        String result = "";

        result = postOrcamento();
        result += " " + postOrcamentoServico();

        cont += getVeiculos();
        cont += getCategorias();
        cont += getServicos();
        cont += getServicoPrecos();
        cont += getOrcamento();
        cont += getOrcamentoServico();

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




    public int getCategorias(){
        Atualizar at = new Atualizar();
        int r = 0;
        try {
            r = at.getCategoria();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int getVeiculos(){
        Atualizar at = new Atualizar();
        int r = 0;
        try {
            r = at.getVeiculo();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int getServicos(){
        Atualizar at = new Atualizar();
        int r = 0;
        try {
            r = 0;at.getServico();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int getServicoPrecos(){
        Atualizar at = new Atualizar();
        int r = 0;
        try {
            r = 0;at.getServicoPreco();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int getOrcamento(){
        Atualizar at = new Atualizar();
        int r = 0;
        try {
            r = 0;at.getOrcamento();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }

    public int getOrcamentoServico(){
        Atualizar at = new Atualizar();
        int r = 0;
        try {
            r = 0;at.getOrcamentoServico();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return r;
    }





}//fim class
