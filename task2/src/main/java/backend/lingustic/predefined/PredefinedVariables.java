package backend.lingustic.predefined;

import backend.functions.factory.FunctionFactory;
import backend.lingustic.Variable;
import backend.lingustic.factory.LinguisticFactory;

import java.util.List;
import java.util.stream.IntStream;

public class PredefinedVariables {

    private PredefinedVariables() {
    }

    public static List<Variable> getPredefinedVariables() {
        List<Double> discreteDomainOfNumberOfLaps = IntStream.range(0, 91).mapToDouble(i -> (double) i).boxed().toList();
        List<Double> discreteDomainOfFinishAndGridPosition = IntStream.range(1, 27).mapToDouble(i -> (double) i).boxed().toList();
        return List.of(
                LinguisticFactory.createVariable(
                        "Liczba przejechanych okrążeń",
                        LinguisticFactory.createLabel("Incydent w wyścigu",
                                FunctionFactory.createTrapezoidalFunction(0, 0, 20, 40, discreteDomainOfNumberOfLaps)),
                        LinguisticFactory.createLabel("Krótki wyścig",
                                FunctionFactory.createTriangularFunction(20, 40, 50, discreteDomainOfNumberOfLaps)),
                        LinguisticFactory.createLabel("Średnia",
                                FunctionFactory.createTriangularFunction(40, 50, 70, discreteDomainOfNumberOfLaps)),
                        LinguisticFactory.createLabel("Powyżej średniej",
                                FunctionFactory.createTrapezoidalFunction(50, 70, 90, 90, discreteDomainOfNumberOfLaps)),
                        LinguisticFactory.createLabel("Wyścig długodystansowy",
                                FunctionFactory.createTrapezoidalFunction(60, 80, 90, 90, discreteDomainOfNumberOfLaps))
                ),
                LinguisticFactory.createVariable(
                        "Liczba zdobytych punktów",
                        LinguisticFactory.createLabel("Nieskuteczny kierowca",
                                FunctionFactory.createTriangularFunction(0, 0, 5, 0, 50)),
                        LinguisticFactory.createLabel("Mało skuteczny kierowca",
                                FunctionFactory.createTriangularFunction(-3, 2, 7, 0, 50)),
                        LinguisticFactory.createLabel("Kierowca punktujący średnio",
                                FunctionFactory.createTriangularFunction(0, 5, 10, 0, 50)),
                        LinguisticFactory.createLabel("Kierowca wysoko punktujący",
                                FunctionFactory.createTriangularFunction(5, 10, 15, 0, 50)),
                        LinguisticFactory.createLabel("Dominator podium",
                                FunctionFactory.createTrapezoidalFunction(11, 16, 50, 50, 0, 50))
                ),
                LinguisticFactory.createVariable(
                        "Wiek kierowcy",
                        LinguisticFactory.createLabel("Początkujący",
                                FunctionFactory.createTriangularFunction(17, 17, 23, 17, 51)),
                        LinguisticFactory.createLabel("Młody",
                                FunctionFactory.createTriangularFunction(17, 23, 29, 17, 51)),
                        LinguisticFactory.createLabel("W średnim wieku",
                                FunctionFactory.createTriangularFunction(23, 29, 35, 17, 51)),
                        LinguisticFactory.createLabel("Dojrzały",
                                FunctionFactory.createTrapezoidalFunction(31, 37, 39, 45, 17, 51)),
                        LinguisticFactory.createLabel("Senior",
                                FunctionFactory.createTrapezoidalFunction(39, 49, 51, 51, 17, 51))
                ),
                LinguisticFactory.createVariable(
                        "Procent punktów zdobytych dla zespołu",
                        LinguisticFactory.createLabel("Kierowca słaby punkt zespołu",
                                FunctionFactory.createGaussianFunction(0, 20, 0, 100)),
                        LinguisticFactory.createLabel("Kierowcy zdominowany przez kolegę z zespołu",
                                FunctionFactory.createTrapezoidalFunction(0, 0, 20, 40, 0, 100)),
                        LinguisticFactory.createLabel("Kierowca równy z kolegą z zespołu",
                                FunctionFactory.createTrapezoidalFunction(25, 45, 55, 75, 0, 100)),
                        LinguisticFactory.createLabel("Kierowca lepszy od kolegi z zespołu",
                                FunctionFactory.createTrapezoidalFunction(60, 80, 100, 100, 0, 100)),
                        LinguisticFactory.createLabel("Kierowca filar zespołu",
                                FunctionFactory.createGaussianFunction(100, 20, 0, 100))
                ),
                LinguisticFactory.createVariable(
                        "Dzień roku wyścigu",
                        LinguisticFactory.createLabel("Na początku sezonu",
                                FunctionFactory.createTrapezoidalFunction(60, 60, 114, 144, 60, 366)),
                        LinguisticFactory.createLabel("W pierwszej połowie sezonu",
                                FunctionFactory.createTrapezoidalFunction(72, 132, 174, 234, 60, 366)),
                        LinguisticFactory.createLabel("W środku sezonu",
                                FunctionFactory.createGaussianFunction(210, 70, 60, 366)),
                        LinguisticFactory.createLabel("W drugiej połowie sezonu",
                                FunctionFactory.createTrapezoidalFunction(192, 252, 294, 354, 60, 366)),
                        LinguisticFactory.createLabel("W końcówce sezonu",
                                FunctionFactory.createTrapezoidalFunction(276, 306, 366, 366, 60, 366))
                ),
                LinguisticFactory.createVariable(
                        "Godzina rozpoczęcia wyścigu",
                        LinguisticFactory.createLabel("W nocy",
                                FunctionFactory.createFunction(x -> {
                                    if (x >= 0 && x <= 14) {
                                        return FunctionFactory.createGaussianFunction(2, 4, 0, 24).getValue(x);
                                    }
                                    return FunctionFactory.createGaussianFunction(26, 4, 0, 24).getValue(x);
                                }, 0, 24)),
                        LinguisticFactory.createLabel("Wieczorem",
                                FunctionFactory.createGaussianFunction(20, 4, 0, 24)),
                        LinguisticFactory.createLabel("Po południu",
                                FunctionFactory.createGaussianFunction(16, 4, 0, 24)),
                        LinguisticFactory.createLabel("W południe",
                                FunctionFactory.createGaussianFunction(12, 4, 0, 24)),
                        LinguisticFactory.createLabel("O poranku",
                                FunctionFactory.createGaussianFunction(8, 4, 0, 24))
                ),
                LinguisticFactory.createVariable(
                        "Najszybszy pitstop",
                        LinguisticFactory.createLabel("Rekordowy",
                                FunctionFactory.createTrapezoidalFunction(0, 0, 10, 25, 0, 3600)),
                        LinguisticFactory.createLabel("Bardzo szybki",
                                FunctionFactory.createTriangularFunction(5, 20, 35, 0, 3600)),
                        LinguisticFactory.createLabel("Średniej długości",
                                FunctionFactory.createTrapezoidalFunction(15, 30, 40, 65, 0, 3600)),
                        LinguisticFactory.createLabel("Długi",
                                FunctionFactory.createGaussianFunction(80, 30, 0, 3600)),
                        LinguisticFactory.createLabel("Przegrywający wyścig",
                                FunctionFactory.createTrapezoidalFunction(55, 105, 3600, 3600, 0, 3600))
                ),
                LinguisticFactory.createVariable(
                        "Najszybsze okrążenie w wyścigu",
                        LinguisticFactory.createLabel("Rekordowe",
                                FunctionFactory.createTriangularFunction(50, 50, 80, 50, 320)),
                        LinguisticFactory.createLabel("Szybkie",
                                FunctionFactory.createTriangularFunction(50, 80, 110, 50, 320)),
                        LinguisticFactory.createLabel("Średnie",
                                FunctionFactory.createTriangularFunction(90, 120, 150, 50, 320)),
                        LinguisticFactory.createLabel("Powyżej średniej",
                                FunctionFactory.createTrapezoidalFunction(110, 160, 320, 320, 50, 320)),
                        LinguisticFactory.createLabel("Bardzo wolne",
                                FunctionFactory.createGaussianFunction(320, 150, 50, 320))
                ),
                LinguisticFactory.createVariable(
                        "Prędkość najszybszego okrążenia",
                        LinguisticFactory.createLabel("Rekordowa",
                                FunctionFactory.createGaussianFunction(300, 80, 0, 300)),
                        LinguisticFactory.createLabel("Wysoka",
                                FunctionFactory.createGaussianFunction(250, 80, 0, 300)),
                        LinguisticFactory.createLabel("W pobliżu średniej",
                                FunctionFactory.createGaussianFunction(180, 80, 0, 300)),
                        LinguisticFactory.createLabel("Niska",
                                FunctionFactory.createGaussianFunction(130, 80, 0, 300)),
                        LinguisticFactory.createLabel("Uszkodzenia",
                                FunctionFactory.createGaussianFunction(60, 80, 0, 300))
                ),
                LinguisticFactory.createVariable(
                        "Najszybsze okrążenie kwalifikacyjne",
                        LinguisticFactory.createLabel("W pobliżu rekordu",
                                FunctionFactory.createTrapezoidalFunction(50, 50, 55, 70, 50, 320)),
                        LinguisticFactory.createLabel("Bardzo szybkie",
                                FunctionFactory.createTrapezoidalFunction(50, 65, 70, 85, 50, 320)),
                        LinguisticFactory.createLabel("W pobliżu średniej",
                                FunctionFactory.createTrapezoidalFunction(65, 80, 120, 170, 50, 320)),
                        LinguisticFactory.createLabel("Wolne",
                                FunctionFactory.createTrapezoidalFunction(85, 135, 180, 230, 50, 320)),
                        LinguisticFactory.createLabel("Nieudane",
                                FunctionFactory.createTrapezoidalFunction(180, 230, 320, 320, 50, 320))
                ),
                LinguisticFactory.createVariable(
                        "Wysokość toru n.p.m.",
                        LinguisticFactory.createLabel("Depresja",
                                FunctionFactory.createTrapezoidalFunction(-100, -100, -50, 50, -100, 9000)),
                        LinguisticFactory.createLabel("Niziny",
                                FunctionFactory.createTrapezoidalFunction(-100, 100, 200, 400, -100, 9000)),
                        LinguisticFactory.createLabel("Wyżyny",
                                FunctionFactory.createTrapezoidalFunction(150, 350, 400, 600, -100, 9000)),
                        LinguisticFactory.createLabel("Teren górzysty",
                                FunctionFactory.createTriangularFunction(350, 550, 750, -100, 9000)),
                        LinguisticFactory.createLabel("Góry",
                                FunctionFactory.createTrapezoidalFunction(450, 950, 9000, 9000, -100, 9000))
                ),
                LinguisticFactory.createVariable(
                        "Klimat toru",
                        LinguisticFactory.createLabel("Równikowy",
                                FunctionFactory.createFunction(x ->
                                        FunctionFactory.createGaussianFunction(0, 30, -90, 90).getValue(Math.abs(x)), -90, 90)),
                        LinguisticFactory.createLabel("Zwrotnikowy",
                                FunctionFactory.createFunction(x ->
                                        FunctionFactory.createGaussianFunction(20, 20, -90, 90).getValue(Math.abs(x)), -90, 90)),
                        LinguisticFactory.createLabel("Podzwrotnikowy",
                                FunctionFactory.createFunction(x ->
                                        FunctionFactory.createGaussianFunction(40, 20, -90, 90).getValue(Math.abs(x)), -90, 90)),
                        LinguisticFactory.createLabel("Umiarkowany",
                                FunctionFactory.createFunction(x ->
                                        FunctionFactory.createGaussianFunction(50, 20, -90, 90).getValue(Math.abs(x)), -90, 90)),
                        LinguisticFactory.createLabel("Okołobiegunowy",
                                FunctionFactory.createFunction(x -> {
                                    if (Math.abs(x) > 70) {
                                        return 1.0;
                                    }
                                    return FunctionFactory.createGaussianFunction(70, 20, -90, 90).getValue(Math.abs(x));
                                }, -90, 90))
                ),
                LinguisticFactory.createVariable(
                        "Miejsce końcowe w wyścigu",
                        LinguisticFactory.createLabel("Na końcu stawki",
                                FunctionFactory.createTrapezoidalFunction(18, 22, 26, 26, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("Na tyle stawki",
                                FunctionFactory.createTrapezoidalFunction(12, 16, 18, 22, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("W środku stawki",
                                FunctionFactory.createTrapezoidalFunction(6, 10, 12, 16, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("W pobliżu strefy punktowej",
                                FunctionFactory.createTrapezoidalFunction(1, 1, 10, 14, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("W czołówce",
                                FunctionFactory.createTrapezoidalFunction(1, 1, 4, 6, discreteDomainOfFinishAndGridPosition))
                ),
                LinguisticFactory.createVariable(
                        "Pozycja startowa",
                        LinguisticFactory.createLabel("Na szarym końcu",
                                FunctionFactory.createTrapezoidalFunction(18, 22, 26, 26, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("Na tyle stawki",
                                FunctionFactory.createTrapezoidalFunction(12, 16, 18, 22, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("W środku stawki",
                                FunctionFactory.createTrapezoidalFunction(6, 10, 12, 16, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("W okolicach 4 rzędu",
                                FunctionFactory.createTrapezoidalFunction(3, 7, 8, 12, discreteDomainOfFinishAndGridPosition)),
                        LinguisticFactory.createLabel("W okolicach pole position",
                                FunctionFactory.createTrapezoidalFunction(1, 1, 2, 6, discreteDomainOfFinishAndGridPosition))
                )
        );
    }
}
