package relatorios;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.swing.JRViewer;
import view.medicinatrabalho.MedicinaTrabalho;

public class GeraRelatorio {
    public void gerarRelatorio(Map<String,Object> params, String tituloJanela, String diretorioRelatorio){
        try {           
            //JasperReport report = (JasperReport) JRLoader.loadObject(stream);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(getClass().getResource(diretorioRelatorio));
            //JasperPrint print = JasperFillManager.fillReport(report,params);
            JasperPrint print = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            
            JRViewer viewer = new JRViewer(print);
            
            JFrame frame = new JFrame();
            frame.setContentPane(viewer);
            frame.setTitle(tituloJanela);
            frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            frame.setVisible(true);
        } catch (JRException ex) {
            Logger.getLogger(MedicinaTrabalho.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
