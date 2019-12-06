package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class DanhGiaTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DanhGia.class);
        DanhGia danhGia1 = new DanhGia();
        danhGia1.setId(1L);
        DanhGia danhGia2 = new DanhGia();
        danhGia2.setId(danhGia1.getId());
        assertThat(danhGia1).isEqualTo(danhGia2);
        danhGia2.setId(2L);
        assertThat(danhGia1).isNotEqualTo(danhGia2);
        danhGia1.setId(null);
        assertThat(danhGia1).isNotEqualTo(danhGia2);
    }
}
