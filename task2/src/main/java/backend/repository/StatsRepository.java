package backend.repository;

import backend.model.Stats;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StatsRepository {
    private List<Stats> stats = new ArrayList<>();
    private final String connectionUrl;
    private final String user;
    private final String password;

    public StatsRepository() {
        Dotenv dotenv = Dotenv.load();
        connectionUrl = dotenv.get("MYSQL_URL");
        user = dotenv.get("MYSQL_USER");
        password = dotenv.get("MYSQL_PASSWORD");
    }

    public List<Stats> getStats() {
        try (Connection conn = DriverManager.getConnection(connectionUrl, user, password);
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
                                .startPosition(rs.getInt("start_position"))
                                .numberOfLaps(rs.getInt("number_of_laps"))
                                .numberOfPoints(rs.getDouble("number_of_points"))
                                .driverAge(rs.getDouble("age_of_the_driver"))
                                .percentageOfPointsGotForATeam(rs.getDouble("percentage_of_points_got_for_a_team"))
                                .fastestLap(rs.getDouble("fastest_lap"))
                                .fastestLapSpeed(rs.getDouble("fastest_lap_speed"))
                                .fastestPitStop(rs.getDouble("fastest_pitstop"))
                                .fastestQualiLap(rs.getDouble("fastest_quali_lap"))
                                .raceData(raceDate == null ? null : raceDate.toLocalDate())
                                .raceTime(raceTime == null ? null : raceTime.toLocalTime())
                                .trackLatitude(rs.getDouble("track_latitude"))
                                .trackAltitude(rs.getDouble("track_altitude"))
                                .build()
                );
            }
            return stats;
        } catch (SQLException e) {
            //ignored
        }
        return Collections.emptyList();
    }
}
