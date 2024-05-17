package kz.nuraly.budget.services;

import kz.nuraly.budget.builders.AssetDtoBuilder;
import kz.nuraly.budget.builders.AssetEntityBuilder;
import kz.nuraly.budget.mappers.AssetsMapper;
import kz.nuraly.budget.repositories.AssetsRepository;
import kz.nuraly.budget.repositories.entities.AssetEntity;
import kz.nuraly.budget.services.dtos.AssetDto;
import kz.nuraly.budget.services.dtos.AssetsDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AssetsServiceTest {

    @Mock
    private AssetsRepository assetsRepository;
    private final AssetsMapper assetsMapper = new AssetsMapper();
    private AssetsService service;

    @BeforeEach
    public void init(){
        service = new AssetsService(assetsRepository, assetsMapper);
    }

    @Test
    void shouldReturnListWithTwoElementsIfThereIsOneElementInDatabase() {
        //given
        int asset = 55;
        AssetEntity assetEntity = new AssetEntityBuilder()
                .withAmount(new BigDecimal(asset))
                .build();

        List<AssetEntity> assetList = Collections.singletonList(assetEntity);
        Mockito.when(assetsRepository.findAll()).thenReturn(assetList);

        //when
        AssetsDto result = service.getAllAssets();

        //then
        List<Integer> listOfAssets = result.getAssets();
        assertThat(listOfAssets)
                .hasSize(1)
                .containsExactly(asset);
    }

    @Test
    void shouldReturnListWithTwoElementsIfThereIsTwoElementInDatabase() {
        //given
        int assetOne = 1;
        int assetTwo = 2;

        AssetEntity entityOne = new AssetEntityBuilder()
                .withAmount(new BigDecimal(assetOne))
                .build();

        AssetEntity entityTwo = new AssetEntityBuilder()
                .withAmount(new BigDecimal(assetTwo))
                .build();

        List<AssetEntity> assetList = Arrays.asList(entityOne, entityTwo);
        Mockito.when(assetsRepository.findAll()).thenReturn(assetList);

        //when
        var result = service.getAllAssets();

        //then
        List<Integer> listOfAssets = result.getAssets();
        assertThat(listOfAssets)
                .hasSize(2)
                .containsExactly(assetOne, assetTwo);
    }

    @Test
    void shouldVerifyIfTheRepositorySaveWasCalledOneTime() {
        //given
        BigDecimal asset = BigDecimal.ONE;
        AssetDto dto = new AssetDtoBuilder()
                .withAmount(asset)
                .build();

        AssetEntity entity = new AssetEntityBuilder()
                .withAmount(asset)
                .build();

        //when
        service.setAsset(dto);

        //then
        Mockito.verify(assetsRepository, Mockito.times(1)).save(entity);
    }

    @Test
    void shouldVerifyIfTheRepositoryDeleteWasCalledOneTIme() {
        //given
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000000");
        BigDecimal testAmount = BigDecimal.ZERO;

        AssetDto dto = new AssetDtoBuilder()
                .withId(id)
                .withAmount(testAmount)
                .build();

        AssetEntity entity = new AssetEntityBuilder()
                .withId(id)
                .withAmount(testAmount)
                .build();

        //when
        service.deleteAsset(dto);

        //then
        Mockito.verify(assetsRepository, Mockito.times(1)).delete(entity);
    }


}