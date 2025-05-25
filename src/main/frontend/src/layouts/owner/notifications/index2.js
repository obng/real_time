import { useState, useEffect } from "react";

// @mui material components
import Grid from "@mui/material/Grid";
import Card from "@mui/material/Card";

// Material Dashboard 2 React components
import MDBox from "components/MDBox";
import MDTypography from "components/MDTypography";
import MDSnackbar from "components/MDSnackbar";

import DashboardLayout from "examples/LayoutContainers/DashboardLayout";
import DashboardNavbar from "examples/Navbars/DashboardNavbar";
import Footer from "examples/Footer";

function Notifications() {
  const [notifications, setNotifications] = useState([]);
  const [openNotification, setOpenNotification] = useState(null);

  // ✅ 로그인된 사장님 ID를 실제로는 auth context 또는 쿠키 등에서 가져오세요.
  const ownerId = 1; // 임시 하드코딩

  useEffect(() => {
    async function fetchNotifications() {
      try {
        const res = await fetch(`/api/notifications/owner/${ownerId}`);
        if (!res.ok) throw new Error("Failed to fetch notifications");

        const data = await res.json();
        setNotifications(data);

        // ✅ 마지막 알림이 기존 알림과 다를 경우에만 스낵바 띄움
        if (
          data.length > 0 &&
          (!openNotification || data[data.length - 1].id !== openNotification.id)
        ) {
          setOpenNotification(data[data.length - 1]);
        }
      } catch (error) {
        console.error("알림을 불러오는 중 오류 발생:", error);
        setOpenNotification({
          id: "error",
          type: "error",
          message: "알림을 불러오는 데 실패했습니다.",
          dateTime: new Date().toISOString(),
        });
      }
    }

    fetchNotifications();
  }, []); // ⛳️ ownerId가 고정이라면 빈 배열로 호출 1회만

  const handleClose = () => setOpenNotification(null);

  const formatDate = (dateTimeStr) => {
    const date = new Date(dateTimeStr);
    return date.toLocaleString(); // 예: "2025. 5. 25. 오후 2:52"
  };

  const renderSnackbar = (notification) => {
    if (!notification) return null;

    let color = "info";
    let icon = "notifications";
    switch (notification.type) {
      case "success":
        color = "success";
        icon = "check_circle";
        break;
      case "warning":
        color = "warning";
        icon = "warning";
        break;
      case "error":
        color = "error";
        icon = "error";
        break;
      default:
        color = "info";
        icon = "notifications";
    }

    return (
      <MDSnackbar
        color={color}
        icon={icon}
        title="새 알림"
        content={notification.message}
        dateTime={formatDate(notification.dateTime)}
        open={Boolean(notification)}
        onClose={handleClose}
        close={handleClose}
        bgWhite
      />
    );
  };

  return (
    <DashboardLayout>
      <DashboardNavbar />
      <MDBox mt={6} mb={3}>
        <Grid container spacing={3} justifyContent="center">
          <Grid item xs={12} lg={8}>
            <Card>
              <MDBox p={2}>
                <MDTypography variant="h5">알림 목록</MDTypography>
              </MDBox>
              <MDBox pt={2} px={2}>
                {notifications.length === 0 ? (
                  <MDTypography variant="body2" color="text">
                    현재 알림이 없습니다.
                  </MDTypography>
                ) : (
                  notifications.map((n) => (
                    <MDTypography
                      key={n.id}
                      variant="body1"
                      color={n.read ? "text.disabled" : "text.primary"}
                      sx={{ mb: 1 }}
                    >
                      [{formatDate(n.dateTime)}] {n.message}
                    </MDTypography>
                  ))
                )}
              </MDBox>
            </Card>
          </Grid>
        </Grid>
      </MDBox>

      {renderSnackbar(openNotification)}

      <Footer />
    </DashboardLayout>
  );
}

export default Notifications;
