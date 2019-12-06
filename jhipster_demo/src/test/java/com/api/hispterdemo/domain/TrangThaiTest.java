package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class TrangThaiTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TrangThai.class);
        TrangThai trangThai1 = new TrangThai();
        trangThai1.setId(1L);
        TrangThai trangThai2 = new TrangThai();
        trangThai2.setId(trangThai1.getId());
        assertThat(trangThai1).isEqualTo(trangThai2);
        trangThai2.setId(2L);
        assertThat(trangThai1).isNotEqualTo(trangThai2);
        trangThai1.setId(null);
        assertThat(trangThai1).isNotEqualTo(trangThai2);
    }
}
