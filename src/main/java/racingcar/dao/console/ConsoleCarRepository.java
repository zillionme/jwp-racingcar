package racingcar.dao.console;

import java.util.concurrent.atomic.AtomicInteger;
import racingcar.dao.CarRepository;
import racingcar.dao.entity.CarEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ConsoleCarRepository implements CarRepository {

    List<CarEntity> cars = new ArrayList<>();
    private AtomicInteger serialNumber = new AtomicInteger(1);

    @Override
    public List<Integer> saveAll(final List<CarEntity> carEntities) {
        List<Integer> carIds = new ArrayList<>();
        for(CarEntity carEntity : carEntities) {
            int carId = serialNumber.get();
            CarEntity car = new CarEntity(carId, carEntity.getName(), carEntity.getPosition(), carEntity.getGameId());
            cars.add(car);
            carIds.add(carId);
            serialNumber.incrementAndGet();
        }
        return carIds;
    }

    @Override
    public List<CarEntity> findCarsByGameID(final int gameId) {
        return cars.stream()
                .filter(car -> car.getGameId() == gameId)
                .collect(Collectors.toList());
    }

}
