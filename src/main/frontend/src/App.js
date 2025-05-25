import { useState, useEffect } from 'react';
import { Routes, Route, Navigate, useLocation } from 'react-router-dom';
import { ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import Icon from '@mui/material/Icon';
import MDBox from 'components/MDBox';
import Sidenav from 'examples/Sidenav';
import Configurator from 'examples/Configurator';
import theme from 'assets/theme';
import themeRTL from 'assets/theme/theme-rtl';
import themeDark from 'assets/theme-dark';
import themeDarkRTL from 'assets/theme-dark/theme-rtl';
import rtlPlugin from 'stylis-plugin-rtl';
import { CacheProvider } from '@emotion/react';
import createCache from '@emotion/cache';
import { useMaterialUIController, setMiniSidenav, setOpenConfigurator } from 'context';
import brandWhite from 'assets/images/logo-ct.png';
import brandDark from 'assets/images/logo-ct-dark.png';

import { useRole } from 'RoleContext';
import { chooseRoute, ownerRoutes, workerRoutes } from './routes';

export default function App() {
  const [controller, dispatch] = useMaterialUIController();
  const {
    miniSidenav,
    direction,
    layout,
    openConfigurator,
    sidenavColor,
    transparentSidenav,
    whiteSidenav,
    darkMode,
  } = controller;
  const [onMouseEnter, setOnMouseEnter] = useState(false);
  const [rtlCache, setRtlCache] = useState(null);
  const { pathname } = useLocation();

  const { role } = useRole();

  useEffect(() => {
    const cacheRtl = createCache({
      key: 'rtl',
      stylisPlugins: [rtlPlugin],
    });
    setRtlCache(cacheRtl);
  }, []);

  const handleOnMouseEnter = () => {
    if (miniSidenav && !onMouseEnter) {
      setMiniSidenav(dispatch, false);
      setOnMouseEnter(true);
    }
  };

  const handleOnMouseLeave = () => {
    if (onMouseEnter) {
      setMiniSidenav(dispatch, true);
      setOnMouseEnter(false);
    }
  };

  const handleConfiguratorOpen = () => setOpenConfigurator(dispatch, !openConfigurator);

  useEffect(() => {
    document.body.setAttribute('dir', direction);
  }, [direction]);

  useEffect(() => {
    document.documentElement.scrollTop = 0;
    if (document.scrollingElement) document.scrollingElement.scrollTop = 0;
  }, [pathname]);

  // 역할에 따라 Sidenav 메뉴 분기
  let sidenavRoutes = [];
  if (role === 'owner') sidenavRoutes = ownerRoutes;
  else if (role === 'worker') sidenavRoutes = workerRoutes;

  // 모든 라우트 한 번에 처리 (Choose + owner + worker)
  const getRoutes = () => {
    const allRoutes = [...chooseRoute, ...ownerRoutes, ...workerRoutes];
    return allRoutes.map((route) =>
      route.route ? (
        <Route path={route.route} element={route.component} key={route.key || route.route} />
      ) : null
    );
  };

  const configsButton = (
    <MDBox
      display="flex"
      justifyContent="center"
      alignItems="center"
      width="3.25rem"
      height="3.25rem"
      bgColor="white"
      shadow="sm"
      borderRadius="50%"
      position="fixed"
      right="2rem"
      bottom="2rem"
      zIndex={99}
      color="dark"
      sx={{ cursor: 'pointer' }}
      onClick={handleConfiguratorOpen}
    >
      <Icon fontSize="small" color="inherit">
        settings
      </Icon>
    </MDBox>
  );

  // Choose 페이지(역할 미선택)에서는 Sidenav을 숨기고 싶다면 layout === 'dashboard' && role !== null 조건 추가
  const SidenavComponent =
    layout === 'dashboard' && role ? (
      <>
        <Sidenav
          color={sidenavColor}
          brand={(transparentSidenav && !darkMode) || whiteSidenav ? brandDark : brandWhite}
          brandName="Material Dashboard 2"
          routes={sidenavRoutes}
          onMouseEnter={handleOnMouseEnter}
          onMouseLeave={handleOnMouseLeave}
        />
        <Configurator />
        {configsButton}
      </>
    ) : layout === 'dashboard' ? (
      <Configurator />
    ) : null;

  if (direction === 'rtl' && !rtlCache) return null;

  const themeMode =
    direction === 'rtl' ? (darkMode ? themeDarkRTL : themeRTL) : darkMode ? themeDark : theme;

  return direction === 'rtl' ? (
    <CacheProvider value={rtlCache}>
      <ThemeProvider theme={themeMode}>
        <CssBaseline />
        {SidenavComponent}
        <Routes>
          <Route path="/" element={<Navigate to="/choose" />} />
          {getRoutes()}
          <Route path="*" element={<Navigate to="/choose" />} />
        </Routes>
      </ThemeProvider>
    </CacheProvider>
  ) : (
    <ThemeProvider theme={themeMode}>
      <CssBaseline />
      {SidenavComponent}
      <Routes>
        <Route path="/" element={<Navigate to="/choose" />} />
        {getRoutes()}
        <Route path="*" element={<Navigate to="/choose" />} />
      </Routes>
    </ThemeProvider>
  );
}
