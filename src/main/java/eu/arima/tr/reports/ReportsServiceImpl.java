package eu.arima.tr.reports;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import eu.arima.tr.workLogs.Worklog;
import eu.arima.tr.workLogs.WorklogRepository;

@Service
public class ReportsServiceImpl implements ReportsService {

	@Autowired
	private WorklogRepository worklogRepository;

	@Override
	public DayStatusSummary getDayStatusSummaryForWorkerAndDay(String workerUserName, LocalDate date) {
		DayStatusSummary status = new DayStatusSummary();
		status.setDate(date);
		status.setWorkerUserName(workerUserName);

		List<Worklog> worklogsForDay = worklogRepository.findByUsernameAndDate(workerUserName, date);
		int totalDuration = 0;
		for (Worklog worklog : worklogsForDay) {
			totalDuration = totalDuration + worklog.getDuration();
		}
		if (totalDuration == 8) {
			status.getStatusList().add(DayStatus.RIGHT_HOURS);
		}
		if (totalDuration > 8) {
			status.getStatusList().add(DayStatus.EXTRA_HOURS);
		}
		if (totalDuration < 8) {
			status.getStatusList().add(DayStatus.MISSING_HOURS);
		}
		return status;
	}

}
