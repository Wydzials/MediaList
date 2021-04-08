package pl.wydzials.medialist.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.comparator.Comparators;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.wydzials.medialist.model.User;
import pl.wydzials.medialist.model.media.Book;
import pl.wydzials.medialist.model.media.Medium;
import pl.wydzials.medialist.model.media.Song;
import pl.wydzials.medialist.repository.UserRepository;
import pl.wydzials.medialist.repository.media.BookRepository;
import pl.wydzials.medialist.repository.media.MediumRepository;
import pl.wydzials.medialist.repository.media.SongRepository;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user/statistics")
public class StatisticsController {

    private final MediumRepository mediumRepository;
    private final BookRepository bookRepository;
    private final SongRepository songRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatisticsController(MediumRepository mediumRepository,
                                BookRepository bookRepository,
                                SongRepository songRepository,
                                UserRepository userRepository) {
        this.mediumRepository = mediumRepository;
        this.bookRepository = bookRepository;
        this.songRepository = songRepository;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String statistics(Model model, Principal principal) {
        User user = userRepository.findByUsername(principal.getName());
        List<Medium> media = mediumRepository.findAllByUserOrderByPriorityDesc(user);
        List<Book> books = bookRepository.findAllByUserOrderByPriorityDesc(user);
        List<Song> songs = songRepository.findAllByUserOrderByPriorityDesc(user);

        getMainStatistics(model, media, books, songs);
        getChartStatistics(model, media);

        return "statistics";
    }

    private void getMainStatistics(Model model, List<Medium> media, List<Book> books, List<Song> songs) {
        int totalTimeInMinutes = media.stream().
                mapToInt(Medium::getTimeInMinutes).sum();

        double averagePriority = media.stream().
                collect(Collectors.averagingDouble(Medium::getPriority));

        int booksPageCount = books.stream().
                mapToInt(Book::getPages).sum();

        long genreCount = songs.stream().
                map(Song::getGenre).distinct().count();

        model.addAttribute("totalCount", media.size());
        model.addAttribute("totalTimeHours", totalTimeInMinutes / 60);
        model.addAttribute("totalTimeMinutes", totalTimeInMinutes % 60);
        model.addAttribute("averagePriority", String.format("%.2f", averagePriority));
        model.addAttribute("booksPageCount", booksPageCount);
        model.addAttribute("genreCount", genreCount);
    }

    private void getChartStatistics(Model model, List<Medium> media) {
        Map<String, Long> countByClass = media.stream()
                .collect(Collectors.groupingBy(medium -> medium.getClass().getSimpleName(), Collectors.counting()));
        Map<String, Integer> timeByClass = media.stream()
                .collect(Collectors.groupingBy(medium -> medium.getClass().getSimpleName(), Collectors.summingInt(Medium::getTimeInMinutes)));

        List<ChartStatisticsDTO> chartStatistics = new ArrayList<>();
        for (String medium : countByClass.keySet()) {
            long count = countByClass.get(medium);
            int time = timeByClass.get(medium) / 60;
            chartStatistics.add(new ChartStatisticsDTO(medium, count, time));
        }

        long countMax = countByClass.values().stream().
                max(Comparators.comparable()).
                orElse(50L);
        long timeMax = timeByClass.values().stream().
                max(Comparators.comparable()).
                orElse(600) / 60;

        model.addAttribute("chartStatistics", chartStatistics);
        model.addAttribute("countMax", countMax);
        model.addAttribute("timeMax", timeMax);
    }

    @ModelAttribute("username")
    public String getVersion(Principal principal) {
        return principal.getName();
    }
}
