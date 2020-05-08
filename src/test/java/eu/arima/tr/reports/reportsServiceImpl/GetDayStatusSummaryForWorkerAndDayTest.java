package eu.arima.tr.reports.reportsServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import eu.arima.tr.reports.DayStatus;
import eu.arima.tr.reports.DayStatusSummary;
import eu.arima.tr.reports.ReportsServiceImpl;
import eu.arima.tr.workLogs.Worklog;
import eu.arima.tr.workLogs.WorklogRepository;


public class GetDayStatusSummaryForWorkerAndDayTest {
	
	@InjectMocks
	ReportsServiceImpl reportsService;
	
	@Mock
	WorklogRepository worklogRepository;
	
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void if_the_worklog_for_the_resquested_day_is_less_than_8_hours_the_status_is_MISSING_HOURS() {
		Worklog worklog = Mockito.mock(Worklog.class);
		Mockito.when(worklog.getDuration()).thenReturn(7);
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(), ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(worklog));
				
		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());
		
		assertEquals(DayStatus.MISSING_HOURS, resultado.getStatusList().get(0));
		
	}
	
	@Test
	public void if_the_worklog_for_the_resquested_day_is_equal_to_8_hours_the_status_is_RIGHT_HOURS() {
		Worklog worklog = Mockito.mock(Worklog.class);
		Mockito.when(worklog.getDuration()).thenReturn(8);
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(), ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(worklog));
				
		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());
		
		assertEquals(DayStatus.RIGHT_HOURS, resultado.getStatusList().get(0));
		
	}
	
	@Test
	public void if_the_worklog_for_the_resquested_day_is_more_than_8_hours_the_status_is_EXTRA_HOURS() {
		Worklog worklog = Mockito.mock(Worklog.class);
		Mockito.when(worklog.getDuration()).thenReturn(10);
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(), ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(worklog));
				
		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());
		
		assertEquals(DayStatus.EXTRA_HOURS, resultado.getStatusList().get(0));
		
	}
	

}
