package xml_chart;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class Chart {
	
	Document doc;
	public Chart(String xml){
		SAXBuilder saxBuilder = new SAXBuilder();
		try {
		    doc = saxBuilder.build(new StringReader(xml));
		} catch (JDOMException e) {
		    // handle JDOMException
		} catch (IOException e) {
		    // handle IOException
		}
	}
	
	public String toString(){
		return doc.getRootElement().getName();
	}
	
	public String getTitle(){
		String tittle = getTitle(doc.getRootElement());
		return tittle;
	}
	
	
	public String getXLabel(){
		for(Element e : doc.getRootElement().getChildren()){
			if(e.getName().equals("plotArea")){
				for(Element e2 : e.getChildren()){
					if(e2.getName().equals("catAx")){
						return getTitle(e2);
					}
				}
			}
		}
		return null;
	}
	
	public String getTitle(Element element){		
		for(Element e : element.getChildren()){
			if(e.getName().equals("title")){
				for(Element e2 : e.getChildren()){
					if(e2.getName().equals("tx")){
						for(Element e3 : e2.getChildren()){
							if(e3.getName().equals("rich")){
								for(Element e4 : e3.getChildren()){
									if(e4.getName().equals("p")){
										for(Element e5 : e4.getChildren()){
											if(e5.getName().equals("r")){
												for(Element e6 : e5.getChildren()){
													if(e6.getName().equals("t")){
														return e6.getValue();
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
				
			}
		}		
		return null;
	}

	public String getYLabel() {
		for(Element e : doc.getRootElement().getChildren()){
			if(e.getName().equals("plotArea")){
				for(Element e2 : e.getChildren()){
					if(e2.getName().equals("valAx")){
						return getTitle(e2);
					}
				}
			}
		}
		return null;
	}
	
	public List<String> getSeriesNames(){
		ArrayList<String> series = new ArrayList<>();
		for(Element e : doc.getRootElement().getChildren()){
			if(e.getName().equals("plotArea")){
				for(Element e2 : e.getChildren()){
					if(e2.getName().contains("Chart")){
						for(Element e3 : e2.getChildren()){
							if(e3.getName().equals("ser")){
								for(Element e4 : e3.getChildren()){
									if(e4.getName().equals("tx")){
										for(Element e5 : e4.getChildren()){
											if(e5.getName().equals("strRef")){
												for(Element e6 : e5.getChildren()){
													if(e6.getName().equals("strCache")){
														for(Element e7 : e6.getChildren()){
															if(e7.getName().equals("pt")){
																for(Element e8 : e7.getChildren()){
																	if(e8.getName().equals("v")){
																		series.add(e8.getValue());
																	}
																}
															}
														}
													}
												}
											}
										}
									}
								}
							}
						}
					}
				}
			}
		}
		return series;
	}
	
	public String getChartType(){
		for(Element e : doc.getRootElement().getChildren()){
			if(e.getName().equals("plotArea")){
				for(Element e2 : e.getChildren()){
					if(e2.getName().contains("Chart")){
						return e2.getName();
					}
				}
			}
		}
		return null;
	}
}
