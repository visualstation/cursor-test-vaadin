package com.example.vaadinlog.ui;

import com.example.vaadinlog.model.LogEntry;
import com.example.vaadinlog.repository.LogEntryRepository;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.data.domain.Sort;

@PageTitle("Logs")
@Route(value = "", layout = MainLayout.class)
public class LogsView extends VerticalLayout {

    private final LogEntryRepository repository;

    public LogsView(LogEntryRepository repository) {
        this.repository = repository;
        setSizeFull();
        Grid<LogEntry> grid = new Grid<>(LogEntry.class, false);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        grid.addColumn(le -> le.getEventTimestamp() != null ? formatter.format(le.getEventTimestamp()) : "")
                .setHeader("Date/Heure")
                .setAutoWidth(true)
                .setSortable(true)
                .setKey("eventTimestamp");
        grid.addColumn(LogEntry::getCustomerId).setHeader("Client").setAutoWidth(true).setSortable(true).setKey("customerId");
        grid.addColumn(LogEntry::getUsername).setHeader("Utilisateur").setAutoWidth(true).setSortable(true).setKey("username");
        grid.addColumn(LogEntry::getMessage).setHeader("Message").setAutoWidth(true).setFlexGrow(1).setKey("message");
        grid.addColumn(LogEntry::getSeconds).setHeader("Secondes").setAutoWidth(true).setSortable(true).setKey("seconds");
        grid.addColumn(LogEntry::getBilledSeconds).setHeader("Facturé (s)").setAutoWidth(true).setSortable(true).setKey("billedSeconds");

        grid.setHeightFull();
        grid.getColumns().forEach(c -> c.setResizable(true));
        grid.setMultiSort(true);

        List<LogEntry> all = repository.findAll(Sort.by(Sort.Direction.ASC, "eventTimestamp"));
        grid.setItems(all);

        add(grid);
        expand(grid);
    }
}
