package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class DuToanTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(DuToan.class);
        DuToan duToan1 = new DuToan();
        duToan1.setId(1L);
        DuToan duToan2 = new DuToan();
        duToan2.setId(duToan1.getId());
        assertThat(duToan1).isEqualTo(duToan2);
        duToan2.setId(2L);
        assertThat(duToan1).isNotEqualTo(duToan2);
        duToan1.setId(null);
        assertThat(duToan1).isNotEqualTo(duToan2);
    }
}
