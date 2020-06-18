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

public class GetDayStatusSummaryForWorkerAndDayTests {

	@InjectMocks
	ReportsServiceImpl reportsService;

	@Mock
	WorklogRepository worklogRepository;

	@Mock
	Worklog oneHourWorklog;
	@Mock
	Worklog twoHoursWorklog;
	@Mock
	Worklog fiveHoursWorklog;
	@Mock
	Worklog eightHoursWorklog;
	@Mock
	Worklog tenHoursWorklog;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);

		Mockito.when(oneHourWorklog.getDuration()).thenReturn(1);
		Mockito.when(twoHoursWorklog.getDuration()).thenReturn(2);
		Mockito.when(fiveHoursWorklog.getDuration()).thenReturn(5);
		Mockito.when(eightHoursWorklog.getDuration()).thenReturn(8);
		Mockito.when(tenHoursWorklog.getDuration()).thenReturn(10);
	}

	@Test
	public void if_the_worklog_for_the_resquested_day_is_less_than_8_hours_the_status_is_MISSING_HOURS() {
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(fiveHoursWorklog));

		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertStatusEquals(DayStatus.MISSING_HOURS, resultado);

	}

	@Test
	public void if_the_sum_of_worklogs_for_the_resquested_day_is_less_than_8_hours_the_status_is_MISSING_HOURS() {
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(fiveHoursWorklog, oneHourWorklog));

		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertStatusEquals(DayStatus.MISSING_HOURS, resultado);

	}

	@Test
	public void if_the_worklog_for_the_resquested_day_is_equal_to_8_hours_the_status_is_RIGHT_HOURS() {
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(eightHoursWorklog));

		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertStatusEquals(DayStatus.RIGHT_HOURS, resultado);

	}

	@Test
	public void if_the_sum_of_worklogs_for_the_resquested_day_is_equal_to_8_hours_the_status_is_RIGHT_HOURS() {
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(LocalDate.class)))
				.thenReturn(Arrays.asList(fiveHoursWorklog, oneHourWorklog, twoHoursWorklog));

		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertStatusEquals(DayStatus.RIGHT_HOURS, resultado);

	}

	@Test
	public void if_the_worklog_for_the_resquested_day_is_more_than_8_hours_the_status_is_EXTRA_HOURS() {
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(LocalDate.class))).thenReturn(Arrays.asList(tenHoursWorklog));

		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertStatusEquals(DayStatus.EXTRA_HOURS, resultado);

	}

	@Test
	public void if_the_sum_of_worklogs_for_the_resquested_day_is_more_than_8_hours_the_status_is_EXTRA_HOURS() {
		Mockito.when(worklogRepository.findByUsernameAndDate(ArgumentMatchers.anyString(),
				ArgumentMatchers.any(LocalDate.class)))
				.thenReturn(Arrays.asList(fiveHoursWorklog, fiveHoursWorklog, oneHourWorklog));

		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertStatusEquals(DayStatus.EXTRA_HOURS, resultado);
	}

	@Test
	public void the_status_result_belongs_to_the_requested_worker() {
		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", LocalDate.now());

		assertEquals("USU", resultado.getWorkerUserName());

	}

	@Test
	public void the_status_result_belongs_to_the_requested_day() {
		LocalDate day = LocalDate.now();
		DayStatusSummary resultado = reportsService.getDayStatusSummaryForWorkerAndDay("USU", day);

		assertEquals(day, resultado.getDate());

	}

	private void assertStatusEquals(DayStatus dayStatus, DayStatusSummary resultado) {
		assertEquals(1, resultado.getStatusList().size());
		assertEquals(dayStatus, resultado.getStatusList().get(0));
	}
}
