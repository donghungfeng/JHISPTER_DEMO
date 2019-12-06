package com.api.hispterdemo.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.api.hispterdemo.web.rest.TestUtil;

public class TienDoTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TienDo.class);
        TienDo tienDo1 = new TienDo();
        tienDo1.setId(1L);
        TienDo tienDo2 = new TienDo();
        tienDo2.setId(tienDo1.getId());
        assertThat(tienDo1).isEqualTo(tienDo2);
        tienDo2.setId(2L);
        assertThat(tienDo1).isNotEqualTo(tienDo2);
        tienDo1.setId(null);
        assertThat(tienDo1).isNotEqualTo(tienDo2);
    }
}
