// routes.js
// layouts
import Choose from 'layouts/choose';
// 사장 루트
import OwnerDashboard from 'layouts/owner/dashboard';
import OwnerNotifications from 'layouts/owner/notifications';
import OwnerProfile from 'layouts/owner/profile';

// 알바 루트
import WorkerDashboard from 'layouts/worker/dashboard';
import WorkerNotifications from 'layouts/worker/notifications';
import WorkerProfile from 'layouts/worker/profile';

// 평가
import EvaluationPage from 'layouts/evaluation/EvaluationPage';
// @mui icons
import Icon from '@mui/material/Icon';

// Choose는 공통 라우트
export const chooseRoute = [
  {
    route: '/choose',
    component: <Choose />,
    key: 'choose',
  },
];

// 사장님 메뉴
export const ownerRoutes = [
  {
    type: 'collapse',
    name: '작업들',
    key: 'owner-dashboard',
    icon: <Icon fontSize="small">dashboard</Icon>,
    route: '/owner/dashboard',
    component: <OwnerDashboard />,
  },
  {
    type: 'collapse',
    name: '알림',
    key: 'owner-notifications',
    icon: <Icon fontSize="small">notifications</Icon>,
    route: '/owner/notifications',
    component: <OwnerNotifications />,
  },


  {
    type: 'collapse',
    name: '나의 프로필',
    key: 'owner-profile',
    icon: <Icon fontSize="small">person</Icon>,
    route: '/owner/profile',
    component: <OwnerProfile />,
  },
  {
    type: 'collapse',
    name: '알바 평가',
    key: 'owner-evaluation',
    icon: <Icon fontSize="small">star</Icon>,
    route: '/evaluation',
    component: <EvaluationPage />,
  },
];

// 알바생 메뉴
export const workerRoutes = [
  {
    type: 'collapse',
    name: '작업들',
    key: 'worker-dashboard',
    icon: <Icon fontSize="small">dashboard</Icon>,
    route: '/worker/dashboard',
    component: <WorkerDashboard />,
  },
  {
    type: 'collapse',
    name: '알림',
    key: 'worker-notifications',
    icon: <Icon fontSize="small">notifications</Icon>,
    route: '/worker/notifications',
    component: <WorkerNotifications />,
  },
  {
    type: 'collapse',
    name: '나의 프로필',
    key: 'worker-profile',
    icon: <Icon fontSize="small">person</Icon>,
    route: '/worker/profile',
    component: <WorkerProfile />,
  },

  {
    type: 'collapse',
    name: '사장 평가',
    key: 'worker-evaluation',
    icon: <Icon fontSize="small">star</Icon>,
    route: '/evaluation',
    component: <EvaluationPage />,
  },
];
