package eu.arima.tr.reports.reportsServiceImpl;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eu.arima.tr.reports.ReportsServiceImpl;
import eu.arima.tr.workLogs.Worklog;
import eu.arima.tr.workLogs.WorklogRepository;

public class GetDayStatusSummaryForWorkerAndDayTests {

	@InjectMocks
	ReportsServiceImpl reportsService;

	@Mock
	WorklogRepository worklogRepository;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void get_status_summary_for_worker_and_day() {
		reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());
		assertTrue(true);
	}

	@Test
	public void calculates_the_status_based_on_worker_and_date_worklogs() {
		List<Worklog> partes = new ArrayList<Worklog>();
		Worklog wl = new Worklog();
		wl.setFromTime(LocalTime.of(8,0,0));
		wl.setToTime(LocalTime.of(19,0,0));
		partes.add(wl);
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(), ArgumentMatchers.any(LocalDate.class))).thenReturn(partes); 
		
		LocalDate fecha = LocalDate.now();
		reportsService.getDayStatusSummaryForWorkerAndDay("USU", fecha);

		Mockito.verify(worklogRepository).findByUsernameAndDate("USU", fecha);
	}
}
