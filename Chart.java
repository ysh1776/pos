package fanal;

import java.awt.*;

import org.jfree.chart.*;
import org.jfree.chart.axis.*;
import org.jfree.chart.labels.*;
import org.jfree.chart.plot.*;

import org.jfree.chart.renderer.category.*;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.category.DefaultCategoryDataset;

import org.jfree.ui.*;
 

public class Chart {
	
	//����Ʈ
    public JFreeChart MonthgetChart(int year) {
      
        // ������ ����
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1

        DBInfo db = DBInfo.getInstance();
        int datasi[] = {0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=0;i<12;i++)
        {
        	datasi[i] = db.Monthchart(year,i+1);
        	 dataset1.addValue(datasi[i], i+1+"��", i+1+"��");
        }
 
        // ������ ���� �� ����
        // ������ ����
        final BarRenderer renderer = new BarRenderer();

        // ���� �ɼ� ����
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       
        // ������ ����
        // �׷��� 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f); // �۾�ü����
        renderer.setSeriesPaint(0, new Color(0,162,255)); // ������

        // plot ����
        final CategoryPlot plot = new CategoryPlot();
        // plot �� ������ ����
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot �⺻ ����
        plot.setOrientation(PlotOrientation.VERTICAL);                // �׷��� ǥ�� ����
        plot.setRangeGridlinesVisible(true);                         // X�� ���̵� ���� ǥ�ÿ���
        plot.setDomainGridlinesVisible(true);                       // Y�� ���̵� ���� ǥ�ÿ���
        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X�� ����
        plot.setDomainAxis(new CategoryAxis());           // X�� ���� ����
        plot.getDomainAxis().setTickLabelFont(axisF);    // X�� ���ݶ� ��Ʈ ����
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);    // ī�װ� �� ��ġ ����
 
        // Y�� ����
        plot.setRangeAxis(new NumberAxis());          // Y�� ���� ����
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
     
        // ���õ� plot�� �������� chart ����
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(year+"�� ���� �Ǹ���Ȳ"); // ��Ʈ Ÿ��Ʋ
        TextTitle copyright = new TextTitle("Month Chart", new Font("SansSerif", Font.PLAIN, 9));
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ
        return chart;
    }
    
    
	//�Ϻ���Ʈ
    public JFreeChart DayChart(int year,int month,int day) {
      
        // ������ ����
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1

        DBInfo db = DBInfo.getInstance();
        int datasi[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=1;i<=day;i++)
        {
        	datasi[i] = db.Daychart(year, month, i);
        	 dataset1.addValue(datasi[i], String.valueOf(month)+"��", String.valueOf(i));
        }
 
        // ������ ���� �� ����
        // ������ ����
        final BarRenderer renderer = new BarRenderer();

        // ���� �ɼ� ����
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       
        // ������ ����
        // �׷��� 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f); // �۾�ü����
        renderer.setSeriesPaint(0, new Color(0,162,255)); // ������
 

        // plot ����
        final CategoryPlot plot = new CategoryPlot();
        // plot �� ������ ����
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot �⺻ ����
        plot.setOrientation(PlotOrientation.VERTICAL);                // �׷��� ǥ�� ����
        plot.setRangeGridlinesVisible(true);                         // X�� ���̵� ���� ǥ�ÿ���
        plot.setDomainGridlinesVisible(true);                       // Y�� ���̵� ���� ǥ�ÿ���
        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X�� ����
        plot.setDomainAxis(new CategoryAxis());           // X�� ���� ����
        plot.getDomainAxis().setTickLabelFont(axisF);    // X�� ���ݶ� ��Ʈ ����
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);    // ī�װ� �� ��ġ ����
 
        // Y�� ����
        plot.setRangeAxis(new NumberAxis());          // Y�� ���� ����
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
     
        // ���õ� plot�� �������� chart ����
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(month+"�� �Ϻ� �Ǹ���Ȳ"); // ��Ʈ Ÿ��Ʋ
        TextTitle copyright = new TextTitle("Day Chart", new Font("SansSerif", Font.PLAIN, 9));
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ
        return chart;
    }
    
    public JFreeChart YearChart(int year) {
        

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();                // line chart 1
 
        // �׷��� 3       
        DBInfo db = DBInfo.getInstance();
        int datasi[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int count=0;
        for(int i=2014; i<=year; i++)
        {
        	datasi[count] = db.Yearchart(i);
        	dataset2.addValue(datasi[count++], "������Ȳ", String.valueOf(i));
        }
        
        // ������ ���� �� ����
        // ������ ����
        final BarRenderer renderer = new BarRenderer();
        final BarRenderer renderer12 = new BarRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
       
        // ���� �ɼ� ����
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(  ItemLabelAnchor.CENTER, TextAnchor.CENTER    );
        final ItemLabelPosition p_below = new ItemLabelPosition( ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       

        // �׷��� 3       
        renderer2.setBaseItemLabelGenerator(generator);
        renderer2.setBaseItemLabelsVisible(true);
        renderer2.setBaseShapesVisible(true);
        renderer2.setDrawOutlines(true);
        renderer2.setUseFillPaint(true);
        renderer2.setBaseFillPaint(Color.WHITE);
        renderer2.setBaseItemLabelFont(f);
        renderer2.setBasePositiveItemLabelPosition(p_below);
        renderer2.setSeriesPaint(0,new Color(219,121,22));
        renderer2.setSeriesStroke(0,new BasicStroke(   2.0f,  BasicStroke.CAP_ROUND,      BasicStroke.JOIN_ROUND, 3.0f) );
       
        // plot ����
        final CategoryPlot plot = new CategoryPlot();
       
        // plot �� ������ ����

        plot.setRenderer(renderer);

        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);
 
        // plot �⺻ ����
        plot.setOrientation(PlotOrientation.VERTICAL);       // �׷��� ǥ�� ����
        plot.setRangeGridlinesVisible(true);                         // X�� ���̵� ���� ǥ�ÿ���
        plot.setDomainGridlinesVisible(true);                      // Y�� ���̵� ���� ǥ�ÿ���
 
        // ������ ���� ���� : dataset ��� ������� ������ ( ��, ���� ����Ѱ� �Ʒ��� �� )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X�� ����
        plot.setDomainAxis(new CategoryAxis());           // X�� ���� ����
        plot.getDomainAxis().setTickLabelFont(axisF); // X�� ���ݶ� ��Ʈ ����
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // ī�װ� �� ��ġ ����
 
        // Y�� ����
        plot.setRangeAxis(new NumberAxis());              // Y�� ���� ����
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y�� ���ݶ� ��Ʈ ����
       
        // ���õ� plot�� �������� chart ����
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("���� �Ǹ���Ȳ"); // ��Ʈ Ÿ��Ʋ
        TextTitle copyright = new TextTitle("Year Chart", new Font("SansSerif", Font.PLAIN, 9));
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(copyright);  // ��Ʈ ���� Ÿ��Ʋ
        return chart;
    }
    
}
