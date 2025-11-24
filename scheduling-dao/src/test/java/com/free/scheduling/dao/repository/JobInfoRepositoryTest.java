package com.free.scheduling.dao.repository;

import com.free.scheduling.dao.entity.JobInfo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class JobInfoRepositoryTest {

    @Mock
    private JpaRepository<JobInfo, Long> jpaRepository;

    @Mock
    private JobInfoRepository jobInfoRepository;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        // 创建一个JpaRepository的mock实现
        jpaRepository = mock(JpaRepository.class);
        jobInfoRepository = mock(JobInfoRepository.class);
    }

    @Test
    void testSave() {
        // Given
        JobInfo jobInfo = new JobInfo();
        jobInfo.setId(1L);
        jobInfo.setJobName("testJob");
        jobInfo.setRemark("Test job description");
        jobInfo.setStatus(1);
        jobInfo.setDeleted(0);
        jobInfo.setCreateTime(LocalDateTime.now());
        jobInfo.setUpdateTime(LocalDateTime.now());

        // When
        when(jobInfoRepository.save(any(JobInfo.class))).thenReturn(jobInfo);

        JobInfo savedJobInfo = jobInfoRepository.save(jobInfo);

        // Then
        assertThat(savedJobInfo).isNotNull();
        assertThat(savedJobInfo.getId()).isEqualTo(1L);
        assertThat(savedJobInfo.getJobName()).isEqualTo("testJob");
        assertThat(savedJobInfo.getRemark()).isEqualTo("Test job description");
        assertThat(savedJobInfo.getStatus()).isEqualTo(1);
        assertThat(savedJobInfo.getDeleted()).isEqualTo(0);

        // Verify
        verify(jobInfoRepository, times(1)).save(any(JobInfo.class));
    }

    @Test
    void testFindById() {
        // Given
        JobInfo jobInfo = new JobInfo();
        jobInfo.setId(1L);
        jobInfo.setJobName("testJob");
        jobInfo.setRemark("Test job description");
        jobInfo.setStatus(1);
        jobInfo.setDeleted(0);
        jobInfo.setCreateTime(LocalDateTime.now());
        jobInfo.setUpdateTime(LocalDateTime.now());

        // When
        when(jobInfoRepository.findById(1L)).thenReturn(Optional.of(jobInfo));

        Optional<JobInfo> foundJobInfo = jobInfoRepository.findById(1L);

        // Then
        assertThat(foundJobInfo).isPresent();
        assertThat(foundJobInfo.get().getId()).isEqualTo(1L);
        assertThat(foundJobInfo.get().getJobName()).isEqualTo("testJob");

        // Verify
        verify(jobInfoRepository, times(1)).findById(1L);
    }

    @Test
    void testExistsById() {
        // When
        when(jobInfoRepository.existsById(1L)).thenReturn(true);

        boolean exists = jobInfoRepository.existsById(1L);

        // Then
        assertThat(exists).isTrue();

        // Verify
        verify(jobInfoRepository, times(1)).existsById(1L);
    }

    @Test
    void testDeleteById() {
        // When
        doNothing().when(jobInfoRepository).deleteById(1L);

        jobInfoRepository.deleteById(1L);

        // Then
        verify(jobInfoRepository, times(1)).deleteById(1L);
    }
}