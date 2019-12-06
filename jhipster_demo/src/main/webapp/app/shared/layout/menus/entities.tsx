import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const EntitiesMenu = props => (
  <NavDropdown icon="th-list" name={translate('global.menu.entities.main')} id="entity-menu">
    <MenuItem icon="asterisk" to="/to-chuc">
      <Translate contentKey="global.menu.entities.toChuc" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/officer">
      <Translate contentKey="global.menu.entities.officer" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/cap-de-tai">
      <Translate contentKey="global.menu.entities.capDeTai" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/trang-thai">
      <Translate contentKey="global.menu.entities.trangThai" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/xep-loai">
      <Translate contentKey="global.menu.entities.xepLoai" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/danh-gia">
      <Translate contentKey="global.menu.entities.danhGia" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/nhan-su-tg">
      <Translate contentKey="global.menu.entities.nhanSuTg" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/du-toan">
      <Translate contentKey="global.menu.entities.duToan" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/tien-do">
      <Translate contentKey="global.menu.entities.tienDo" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/file-attach">
      <Translate contentKey="global.menu.entities.fileAttach" />
    </MenuItem>
    <MenuItem icon="asterisk" to="/de-tai">
      <Translate contentKey="global.menu.entities.deTai" />
    </MenuItem>
    {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
  </NavDropdown>
);
