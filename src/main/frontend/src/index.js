import React from 'react';
import { createRoot } from 'react-dom/client';
import { BrowserRouter } from 'react-router-dom';
import App from 'App';

// Material Dashboard 2 React Context Provider
import { MaterialUIControllerProvider } from 'context';

// 역할 관리 Context Provider 추가
import { RoleProvider } from 'RoleContext'; // 경로는 실제 파일 위치에 맞게 조정

const container = document.getElementById('app');
const root = createRoot(container);

root.render(
  <BrowserRouter>
    <MaterialUIControllerProvider>
      <RoleProvider>
        <App />
      </RoleProvider>
    </MaterialUIControllerProvider>
  </BrowserRouter>
);
