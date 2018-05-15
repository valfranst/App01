package teanlabsvalfran.app01.Impressao;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Build;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.ParcelFileDescriptor;
import android.print.PageRange;
import android.print.PrintAttributes;
import android.print.PrintDocumentAdapter;
import android.print.PrintDocumentInfo;
import android.print.pdf.PrintedPdfDocument;
import android.support.annotation.RequiresApi;

import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import teanlabsvalfran.app01.AppDatabase;
import teanlabsvalfran.app01.R;
import teanlabsvalfran.app01.models.OSPersonalizado;
import teanlabsvalfran.app01.models.Orcamento;
import teanlabsvalfran.app01.models.OrcamentoDao;
import teanlabsvalfran.app01.models.OrcamentoServicoDao;
import teanlabsvalfran.app01.models.VeiculoDao;

/**
 * Created by Valfran on 22/03/2018.
 */

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class MyPrintDocumentAdapter extends PrintDocumentAdapter {

    Context context;
    private int pageHeight;
    private int pageWidth;
    public PdfDocument myPdfDocument;
    public int totalpages = 1;

    private AppDatabase mDb;
    private VeiculoDao mVeiculoDao;
    private OrcamentoDao mOrcamento;
    private OrcamentoServicoDao mOServico;

    private List<OSPersonalizado> osps;
    private Orcamento orcamento;
    private String placa;

    private DateFormat df;

    public MyPrintDocumentAdapter(Context context, List<OSPersonalizado> osps, Orcamento orcamento, String placa){
        this.context = context;

        mDb = AppDatabase.getDatabase(context);
        mVeiculoDao = mDb.veiculoDao();
        mOrcamento = mDb.orcamentoDao();
        mOServico = mDb.orcamentoServicoDao();

        df = new SimpleDateFormat("dd/MM/yyyy kk:mm");

        this.osps = osps;
        this.orcamento = orcamento;
        this.placa = placa;
    }


    @Override
    public void onLayout(PrintAttributes oldAttributes, PrintAttributes newAttributes, CancellationSignal cancellationSignal,
                         LayoutResultCallback callback, Bundle metadata) {

        myPdfDocument = new PrintedPdfDocument(context, newAttributes);
        pageHeight = newAttributes.getMediaSize().getHeightMils()/1000 * 72;
        pageWidth = newAttributes.getMediaSize().getWidthMils()/1000 * 72;

        if (cancellationSignal.isCanceled() ) {
            callback.onLayoutCancelled();
            return;
        }


        //*************************DEFINIR NUMERO DE PAGINAS BASEADO NA QUANTIDADE DE SERVIÇOS ASSOCIADOS AO VEICULO
        int itens = osps.size();
        if(itens > 18 && itens < 37){
            totalpages = 2;
        }else if(itens > 36 && itens < 55){
            totalpages = 3;
        }else if(itens > 54){
            totalpages = 4;
        }

        //**********************************************************************************************************



        if (totalpages > 0) {
            PrintDocumentInfo.Builder builder = new PrintDocumentInfo
                    .Builder("Orcamento_"+orcamento.getOrcamentoId()+".pdf")
                    .setContentType(PrintDocumentInfo.CONTENT_TYPE_DOCUMENT)
                    .setPageCount(totalpages);

            PrintDocumentInfo info = builder.build();
            callback.onLayoutFinished(info, true);
        } else {
            callback.onLayoutFailed("Page count is zero.");
        }


    }//FIM onLayout

    @Override
    public void onWrite(final PageRange[] pageRanges, final ParcelFileDescriptor destination, final CancellationSignal cancellationSignal,
                        final WriteResultCallback callback) {

        for (int i = 0; i < totalpages; i++) {
            if (pageInRange(pageRanges, i))
            {
                PdfDocument.PageInfo newPage = new PdfDocument.PageInfo.Builder(pageWidth,
                        pageHeight, i).create();

                PdfDocument.Page page = myPdfDocument.startPage(newPage);

                if (cancellationSignal.isCanceled()) {
                    callback.onWriteCancelled();
                    myPdfDocument.close();
                    myPdfDocument = null;
                    return;
                }
                drawPage(page, i);
                myPdfDocument.finishPage(page);
            }
        }

        try {
            myPdfDocument.writeTo(new FileOutputStream(
                    destination.getFileDescriptor()));
        } catch (IOException e) {
            callback.onWriteFailed(e.toString());
            return;
        } finally {
            myPdfDocument.close();
            myPdfDocument = null;
        }

        callback.onWriteFinished(pageRanges);
    }//FIM onLayout

    private boolean pageInRange(PageRange[] pageRanges, int page)
    {
        for (int i = 0; i<pageRanges.length; i++)
        {
            if ((page >= pageRanges[i].getStart()) &&
                    (page <= pageRanges[i].getEnd()))
                return true;
        }
        return false;
    }//FIM pageInRange

    private void drawPage(PdfDocument.Page page, int pagenumber) {
        Canvas canvas = page.getCanvas();

        pagenumber++; // Make sure page numbers start at 1

        int titleBaseLine = 30;
        int leftMargin = 100;





        //**************************CABECALHO & RODAPÉ
        Paint paint3 = new Paint(Paint.FILTER_BITMAP_FLAG);
        paint3.setColor(Color.BLACK);
        paint3.setTextSize(7);

        Resources res = context.getResources();
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        //options.inSampleSize = 3;
        options.inScaled = false;


        Bitmap bitmap = BitmapFactory.decodeResource(res, R.drawable.logo01, options);
        canvas.drawBitmap(bitmap,10,10, paint3);

        canvas.drawText("GIGA AUTO VIDROS - R. Artur Rios, 270 - Sen. Vasconcelos, Rio de Janeiro / Tels.: (21) 2394-3257 / 98900-6287", leftMargin+10, titleBaseLine, paint3);

        canvas.drawText("PARADA DOS VIDROS I (BENTO RIBEIRO) - Rua Carolina Machado, 1612 - Loja A - Bento Ribeiro - RJ / Tels.: (21) 3390-3414 / 98901-9906", leftMargin-70, titleBaseLine+720, paint3);
        canvas.drawText("PARADA DOS VIDROS II (JACAREPAGUÁ) - Estrada do Gabinal, 1731 B - RJ / Tels.: (21) 2394-3257 - 98900-6287", leftMargin-20, titleBaseLine+740, paint3);

        paint3.setStyle(Paint.Style.STROKE);
        paint3.setStrokeWidth(1);
        canvas.drawLine(leftMargin+10,titleBaseLine+10, leftMargin+440, titleBaseLine+10,paint3);
        canvas.drawLine(leftMargin-70,titleBaseLine+705, leftMargin+460, titleBaseLine+705,paint3);



        //**************************TITULO
        Paint paint2 = new Paint();
        titleBaseLine = 60;

        paint2.setColor(Color.BLACK);
        paint2.setTextSize(13);
        canvas.drawText("PÁGINA 0"+pagenumber+"/"+totalpages, 480, titleBaseLine, paint2);

        paint2.setColor(Color.BLUE);
        paint2.setTextSize(20);
        paint2.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));

        if(pagenumber == 1) {
            titleBaseLine = 85;
            canvas.drawText("ORÇAMENTO DE SERVIÇOS AUTOMOTIVOS", 90, titleBaseLine, paint2);
        }




        //*************************DADOS ORÇAMENTO
        titleBaseLine+=40;
        leftMargin = 60;
        paint2.setTextSize(13);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(2);

        canvas.drawRect(20, titleBaseLine-20, 560, titleBaseLine+15, paint2);

        paint2.setStyle(Paint.Style.FILL);
        paint2.setStrokeWidth(0);

        canvas.drawText("VEICULO: "+placa+"          DATA: "+df.format(orcamento.getDataAbertura())+"          VALOR TOTAL: "+
                orcamento.getValorTotal()+" R$", leftMargin, titleBaseLine, paint2);






        //*************************DESCRICAO DA LISTA

        titleBaseLine+=40;
        leftMargin = 20;
        /*paint.setStyle(Paint.Style.FILL_AND_STROKE);
        paint.setStrokeWidth(10);*/
        paint2.setColor(Color.BLACK);
        paint2.setTextSize(12);
        canvas.drawText("SERVIÇOS", leftMargin, titleBaseLine, paint2);
        canvas.drawText("TIPO DE VEICULO", leftMargin+270, titleBaseLine, paint2);
        canvas.drawText("VALOR", leftMargin+480, titleBaseLine, paint2);



        //*************************LOOP LISTA ITEMS ORCAMENTO
        Paint paint = new Paint();
        paint.setTextSize(10);
        paint.setColor(Color.BLACK);
        titleBaseLine+=30;

        int i = 0, freio = 16;
        if(pagenumber == 2){
            i = 18;
            freio = 34;
        }else if(pagenumber == 3){
            i = 36;
            freio = 52;
        }else if(pagenumber == 4){
            i = 54;
            freio = 70;
        }

        for(;i < osps.size(); i++){
            OSPersonalizado osp = osps.get(i);
            canvas.drawLine(20, titleBaseLine-20, 550, titleBaseLine-20, paint2);

            canvas.drawText(osp.getNomeServico()+"", leftMargin, titleBaseLine, paint);
            canvas.drawText(osp.getDescricao()+"", leftMargin+270, titleBaseLine, paint);
            canvas.drawText(osp.getValor()+"", leftMargin+480, titleBaseLine, paint);

            titleBaseLine+=30;
            if(i > freio){
                break;
            }

        }


    }//FIM drawPage











    //*************
}//FIM CLASS
