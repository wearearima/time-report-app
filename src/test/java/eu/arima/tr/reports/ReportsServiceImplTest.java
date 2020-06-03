package eu.arima.tr.reports;

import eu.arima.tr.workLogs.WorklogRepository;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static java.util.Collections.emptyList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ReportsServiceImplTest {

    @Test
    public void itWorks() {
        WorklogRepository worklogRepository = mock(WorklogRepository.class);
        ReportsService reportsService = new ReportsServiceImpl(mock(WorklogRepository.class));

        String username = "test";
        LocalDate today = LocalDate.now();

        when(worklogRepository.findByUsernameAndDate(username, today)).thenReturn(emptyList());

        DayStatusSummary summary = reportsService.getDayStatusSummaryForWorkerAndDay(username, today);

        assertNotNull(summary);
    }

}
