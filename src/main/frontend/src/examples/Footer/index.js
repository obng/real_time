// @mui material components
import Link from "@mui/material/Link";
// Material Dashboard 2 React components
import MDBox from "components/MDBox";
// Material Dashboard 2 React base styles
import typography from "assets/theme/base/typography";

function Footer() {
  const { size } = typography;

  return (
    <MDBox
      width="100%"
      display="flex"
      flexDirection="column"
      justifyContent="center"
      alignItems="center"
      px={1.5}
    >
      <MDBox
        display="flex"
        justifyContent="center"
        alignItems="center"
        flexWrap="wrap"
        color="text"
        fontSize={size.sm}
        px={1.5}
      >
        &copy; {new Date().getFullYear()} 캡스톤 디자인 과제 by 헬퍼
      </MDBox>
      <MDBox
        display="flex"
        justifyContent="center"
        alignItems="center"
        flexWrap="wrap"
        color="text"
        fontSize={size.sm}
        px={1.5}
        mt={0.5}
      >
        <Link
          href="https://www.creative-tim.com/product/material-dashboard-react"
          target="_blank"
          rel="noopener"
          color="inherit"
          underline="hover"
        >
          탬플릿 by Material Dashboard 2 React
        </Link>
      </MDBox>
    </MDBox>
  );
}

export default Footer;
