package eu.arima.tr.reports;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DayStatusSummary {

	private LocalDate date;
	private String workerUserName;
	private List<DayStatus> statusList = new ArrayList<DayStatus>();

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getWorkerUserName() {
		return workerUserName;
	}

	public void setWorkerUserName(String workerUserName) {
		this.workerUserName = workerUserName;
	}

	public List<DayStatus> getStatusList() {
		return statusList;
	}

	public void setStatusList(List<DayStatus> statusList) {
		this.statusList = statusList;
	}

}
