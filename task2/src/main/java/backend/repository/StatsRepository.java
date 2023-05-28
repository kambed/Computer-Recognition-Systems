package backend.repository;

import backend.model.Stats;
import io.github.cdimascio.dotenv.Dotenv;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class StatsRepository {

    private StatsRepository() {
    }
    private static final Boolean DB_MODE = false;
    private static final List<Stats> stats = new ArrayList<>();

    public static List<Stats> getStats() {
        if (!stats.isEmpty()) {
            return new ArrayList<>(stats);
        }
        if (Boolean.TRUE.equals(DB_MODE)) {
            loadStatsFromDb();
        } else {
            loadStatsFromBinary();
        }
        return new ArrayList<>(stats);
    }

    private static void loadStatsFromBinary() {
        try (
                InputStream fileIn = StatsRepository.class.getResourceAsStream("/f1_stats.bin");
                ObjectInputStream in = new ObjectInputStream(fileIn);
        ) {
            //noinspection unchecked
            stats.addAll((List<Stats>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            throw new DataImportException("Error while importing data from binary file: " + e.getMessage());
        }
    }

    private static void loadStatsFromDb() {
        Dotenv dotenv = Dotenv.load();
        try (Connection conn = DriverManager.getConnection(
                dotenv.get("MYSQL_URL"),
                dotenv.get("MYSQL_USER"),
                dotenv.get("MYSQL_PASSWORD"));
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM stats");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Time raceTime = rs.getTime("race_time");
                Date raceDate = rs.getDate("race_date");
                stats.add(
                        Stats.builder()
                                .id(rs.getInt("id"))
                                .driver(rs.getString("driver"))
                                .team(rs.getString("team"))
                                .track(rs.getString("track"))
                                .year(rs.getString("year"))
                                .finishPosition(rs.getInt("finish_position"))
                                .startPosition(rs.getInt("start_position") == 34 ? 26 : rs.getInt("start_position"))
                                .numberOfLaps(rs.getInt("number_of_laps"))
                                .numberOfPoints(rs.getDouble("number_of_points"))
                                .driverAge(rs.getDouble("age_of_the_driver"))
                                .percentageOfPointsGotForATeam(rs.getDouble("percentage_of_points_got_for_a_team"))
                                .fastestLap(rs.getDouble("fastest_lap") == 0 ? null : rs.getDouble("fastest_lap"))
                                .fastestLapSpeed(rs.getDouble("fastest_lap_speed"))
                                .fastestPitStop(rs.getDouble("fastest_pitstop"))
                                .fastestQualiLap(rs.getDouble("fastest_quali_lap") == 0 ? null : rs.getDouble("fastest_quali_lap"))
                                .raceData(raceDate == null ? null : raceDate.toLocalDate().getDayOfYear())
                                .raceTime(raceTime == null ? null : raceTime.toLocalTime().getHour() + raceTime.toLocalTime().getMinute() / 60.0)
                                .trackLatitude(rs.getDouble("track_latitude"))
                                .trackAltitude(rs.getDouble("track_altitude"))
                                .build()
                );
            }
        } catch (SQLException e) {
            throw new DataImportException("Error while importing data from database: " + e.getMessage());
        }
    }
}
