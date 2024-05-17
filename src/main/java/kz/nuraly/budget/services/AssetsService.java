package kz.nuraly.budget.services;

import kz.nuraly.budget.mappers.AssetsMapper;
import kz.nuraly.budget.repositories.AssetsRepository;
import kz.nuraly.budget.repositories.entities.AssetEntity;
import kz.nuraly.budget.services.dtos.AssetDto;
import kz.nuraly.budget.services.dtos.AssetsDto;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class AssetsService {
    private final AssetsRepository assetsRepository;
    private final AssetsMapper assetsMapper;

    public AssetsService(AssetsRepository assetsRepository, AssetsMapper assetsMapper) {
        this.assetsRepository = assetsRepository;
        this.assetsMapper = assetsMapper;
    }


    public AssetsDto getAllAssets() {
        List<AssetEntity> all = assetsRepository.findAll();
        List<Integer> assetsAmount = all.stream()
                .map(entity -> entity.getAmount().intValue())
                .collect(Collectors.toList());
        var dto = new AssetsDto();
        dto.setAssets(assetsAmount);

        return dto;
    }

    public void setAsset(AssetDto dto) {
        AssetEntity entity = assetsMapper.fromDtoToEntity(dto);
        assetsRepository.save(entity);
    }

    public void deleteAsset(AssetDto dto) {
        AssetEntity entity = assetsMapper.fromDtoToEntity(dto);
        assetsRepository.delete(entity);
    }
}
