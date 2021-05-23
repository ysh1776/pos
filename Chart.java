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
	
	//월차트
    public JFreeChart MonthgetChart(int year) {
      
        // 데이터 생성
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1

        DBInfo db = DBInfo.getInstance();
        int datasi[] = {0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=0;i<12;i++)
        {
        	datasi[i] = db.Monthchart(year,i+1);
        	 dataset1.addValue(datasi[i], i+1+"월", i+1+"월");
        }
 
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();

        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       
        // 렌더링 세팅
        // 그래프 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f); // 글씨체정의
        renderer.setSeriesPaint(0, new Color(0,162,255)); // 색정의

        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
        // plot 에 데이터 적재
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);                // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);                         // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);                       // Y축 가이드 라인 표시여부
        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());           // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF);    // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);    // 카테고리 라벨 위치 조정
 
        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());          // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정
     
        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(year+"년 월별 판매현황"); // 차트 타이틀
        TextTitle copyright = new TextTitle("Month Chart", new Font("SansSerif", Font.PLAIN, 9));
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(copyright);  // 차트 서브 타이틀
        return chart;
    }
    
    
	//일별차트
    public JFreeChart DayChart(int year,int month,int day) {
      
        // 데이터 생성
        DefaultCategoryDataset dataset1 = new DefaultCategoryDataset();                // bar chart 1

        DBInfo db = DBInfo.getInstance();
        int datasi[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        for(int i=1;i<=day;i++)
        {
        	datasi[i] = db.Daychart(year, month, i);
        	 dataset1.addValue(datasi[i], String.valueOf(month)+"월", String.valueOf(i));
        }
 
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();

        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(
                ItemLabelAnchor.CENTER, TextAnchor.CENTER );
        final ItemLabelPosition p_below = new ItemLabelPosition(
                     ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       
        // 렌더링 세팅
        // 그래프 1
        renderer.setBaseItemLabelGenerator(generator);
        renderer.setBaseItemLabelsVisible(true);
        renderer.setBasePositiveItemLabelPosition(p_center);
        renderer.setBaseItemLabelFont(f); // 글씨체정의
        renderer.setSeriesPaint(0, new Color(0,162,255)); // 색정의
 

        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
        // plot 에 데이터 적재
        plot.setDataset(dataset1);
        plot.setRenderer(renderer);

        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);                // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);                         // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);                       // Y축 가이드 라인 표시여부
        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());           // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF);    // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);    // 카테고리 라벨 위치 조정
 
        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());          // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정
     
        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle(month+"월 일별 판매현황"); // 차트 타이틀
        TextTitle copyright = new TextTitle("Day Chart", new Font("SansSerif", Font.PLAIN, 9));
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(copyright);  // 차트 서브 타이틀
        return chart;
    }
    
    public JFreeChart YearChart(int year) {
        

        DefaultCategoryDataset dataset2 = new DefaultCategoryDataset();                // line chart 1
 
        // 그래프 3       
        DBInfo db = DBInfo.getInstance();
        int datasi[] = {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0};
        int count=0;
        for(int i=2014; i<=year; i++)
        {
        	datasi[count] = db.Yearchart(i);
        	dataset2.addValue(datasi[count++], "역대현황", String.valueOf(i));
        }
        
        // 렌더링 생성 및 세팅
        // 렌더링 생성
        final BarRenderer renderer = new BarRenderer();
        final BarRenderer renderer12 = new BarRenderer();
        final LineAndShapeRenderer renderer2 = new LineAndShapeRenderer();
       
        // 공통 옵션 정의
        final CategoryItemLabelGenerator generator = new StandardCategoryItemLabelGenerator();
        final ItemLabelPosition p_center = new ItemLabelPosition(  ItemLabelAnchor.CENTER, TextAnchor.CENTER    );
        final ItemLabelPosition p_below = new ItemLabelPosition( ItemLabelAnchor.OUTSIDE6, TextAnchor.TOP_LEFT);
        Font f = new Font("Gulim", Font.BOLD, 14);
        Font axisF = new Font("Gulim", Font.PLAIN, 14);
       

        // 그래프 3       
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
       
        // plot 생성
        final CategoryPlot plot = new CategoryPlot();
       
        // plot 에 데이터 적재

        plot.setRenderer(renderer);

        plot.setDataset(2, dataset2);
        plot.setRenderer(2, renderer2);
 
        // plot 기본 설정
        plot.setOrientation(PlotOrientation.VERTICAL);       // 그래프 표시 방향
        plot.setRangeGridlinesVisible(true);                         // X축 가이드 라인 표시여부
        plot.setDomainGridlinesVisible(true);                      // Y축 가이드 라인 표시여부
 
        // 렌더링 순서 정의 : dataset 등록 순서대로 렌더링 ( 즉, 먼저 등록한게 아래로 깔림 )
        plot.setDatasetRenderingOrder(DatasetRenderingOrder.FORWARD);
       
        // X축 세팅
        plot.setDomainAxis(new CategoryAxis());           // X축 종류 설정
        plot.getDomainAxis().setTickLabelFont(axisF); // X축 눈금라벨 폰트 조정
        plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.STANDARD);       // 카테고리 라벨 위치 조정
 
        // Y축 세팅
        plot.setRangeAxis(new NumberAxis());              // Y축 종류 설정
        plot.getRangeAxis().setTickLabelFont(axisF);  // Y축 눈금라벨 폰트 조정
       
        // 세팅된 plot을 바탕으로 chart 생성
        final JFreeChart chart = new JFreeChart(plot);
        chart.setTitle("역대 판매현황"); // 차트 타이틀
        TextTitle copyright = new TextTitle("Year Chart", new Font("SansSerif", Font.PLAIN, 9));
        copyright.setHorizontalAlignment(HorizontalAlignment.RIGHT);
        chart.addSubtitle(copyright);  // 차트 서브 타이틀
        return chart;
    }
    
}
