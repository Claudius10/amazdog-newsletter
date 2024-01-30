package com.amazdog.amazdognewsletterapi.entities.dtos.chart;

import com.amazdog.amazdognewsletterapi.entities.Statistic;

import java.util.ArrayList;
import java.util.List;

public class ChartData {

	private String title;

	private String type;

	private List<List<Statistic>> statisticList;

	public ChartData(String title, String type, List<List<Statistic>> statisticList) {
		this.title = title;
		this.type = type;
		this.statisticList = new ArrayList<>(statisticList);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<List<Statistic>> getStatisticList() {
		return statisticList;
	}

	public void setStatisticList(List<List<Statistic>> statisticList) {
		this.statisticList = statisticList;
	}
}
